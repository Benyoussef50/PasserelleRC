package com.structis.RC.service;

import java.io.IOException;

import com.google.api.services.drive.Drive;
import com.structis.RC.Utils.GoogleDriveUtils;

public class DriveService {

	private Drive driveService;

	public DriveService() throws IOException {
		this.driveService = GoogleDriveUtils.getDriveService();
	}

	public Drive getService() {
		return driveService;
	}

	public void setService(Drive service) {
		this.driveService = service;
	}

}
