package com.nestandrest.util;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.http.Part;

/**
 * Utility class for handling image file uploads.
 * <p>
 * This class provides methods for extracting the file name from a {@link Part}
 * object and uploading the image file to a specified directory on the server.
 * </p>
 */
public class ImageUtil {

	/**
	 * Uploads the image file from the given {@link Part} object to a specified
	 * directory on the server.
	 * 
	 * <p>
	 * This method ensures that the directory where the file will be saved exists
	 * and creates it if necessary. It writes the uploaded file to the server's file
	 * system. Returns {@code true} if the upload is successful, and {@code false}
	 * otherwise.
	 * </p>
	 * 
	 * @param part the {@link Part} object representing the uploaded image file.
	 * @return {@code true} if the file was successfully uploaded, {@code false}
	 *         otherwise.
	 */
	public boolean uploadImage(Part part, String saveFolder, int fileId) {
		String savePath = getSavePath(saveFolder);
		File fileSaveDir = new File(savePath);

		// Ensure the directory exists
		if (!fileSaveDir.exists()) {
			if (!fileSaveDir.mkdir()) {
				return false; // Failed to create the directory
			}
		}
		try {
			// Create the file path
			String filePath = savePath + "/" + fileId + ".png";
			// Write the file to the server
			part.write(filePath);
			return true; // Upload successful
		} catch (IOException e) {
			e.printStackTrace(); // Log the exception
			return false; // Upload failed
		}
	}

	public String getSavePath(String saveFolder) {
		return "/home/safal/eclipse-workspace/nest-and-rest/src/main/webapp/resources/" + saveFolder + "/";
	}
}