package com.structis.RC.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.structis.RC.Utils.GoogleDriveUtils;

public class DownloadGoogleFile {

	public static void downloadGoogleFile(String fullName) throws IOException {
		List<File> list = SearchGoogleFile.getGoogleFilesByName(fullName);
		for (File folder : list) {
			downloadGoogleFileId(folder.getId(), folder.getName());
			System.out.println("ID : " + folder.getId() + " --- Name: " + folder.getName() + " --- link: "
					+ folder.getWebContentLink() + "---" + folder.getFileExtension());
			System.out.println(folder.getName()+":  Done!");
		}
	}

	public static void downloadGoogleFileId(String fileId, String fileName) throws IOException {
		Drive driveService = GoogleDriveUtils.getDriveService();
		OutputStream out = new FileOutputStream("C:\\downloads_rc\\"+fileName);
		driveService.files().get(fileId).executeMediaAndDownloadTo(out);
	}

	public static InputStream downloadFile() throws IOException {
		Drive driveService = GoogleDriveUtils.getDriveService();
		String url = "https://drive.google.com/uc?id=1_9qg4CWpofkwN0RWPeq1c9HbnGAbYCGA&export=download";
		HttpResponse resp = driveService.getRequestFactory().buildGetRequest(new GenericUrl(url)).execute();
		return resp.getContent();
	}

	public static void main(String[] args) throws IOException {
	//	downloadGoogleFile("dql_query");
		 downloadFile();
		// downloadGoogleFileId();
	}

}
