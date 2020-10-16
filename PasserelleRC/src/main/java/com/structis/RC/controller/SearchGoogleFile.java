package com.structis.RC.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.structis.RC.Utils.GoogleDriveUtils;

public class SearchGoogleFile {
	// com.google.api.services.drive.model.File
	public static List<File> getGoogleFilesByName(String fileNameLike) throws IOException {
		Drive driveService = GoogleDriveUtils.getDriveService();
		String pageToken = null;
		List<File> list = new ArrayList<File>();

		String query = " name contains '" + fileNameLike + "' " //
				+ " and mimeType != 'application/vnd.google-apps.folder' ";

		do {
			FileList result = driveService.files().list().setQ(query).setSpaces("drive") //
					// Fields will be assigned values: id, name, createdTime, mimeType
					.setFields(
							"nextPageToken, files(id, name, createdTime, mimeType, webContentLink, webViewLink, fileExtension)")//
					.setPageToken(pageToken).execute();
			for (File file : result.getFiles()) {
				list.add(file);
			}
			pageToken = result.getNextPageToken();
		} while (pageToken != null);
		return list;
	}

	public static void main(String[] args) throws IOException {
		List<File> rootGoogleFolders = getGoogleFilesByName("pfe");
		for (File folder : rootGoogleFolders) {
			System.out.println("Mime Type: " + folder.getMimeType() + " --- Name: " + folder.getName());
		}
		System.out.println("Done!");
	}

}
