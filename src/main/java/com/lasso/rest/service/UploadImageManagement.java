package com.lasso.rest.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * The Interface UploadImageManagement.
 *
 * @author Paul Mai
 */
public interface UploadImageManagement {

	/**
	 * Adds the watermark.
	 *
	 * @param __sourceImageFile the source image file
	 * @param __watermarkImageFile the watermark image file
	 * @param __destinationImageFile the destination image file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void addWatermark(File __sourceImageFile, File __watermarkImageFile,
			File __destinationImageFile) throws IOException;

	/**
	 * Generate image name.
	 *
	 * @param __extension the extension
	 * @return the string
	 */
	public String generateImageName(String __extension);

	/**
	 * Resize image.
	 *
	 * @param __sourceFile the source file
	 * @param __destinationFile the destination file
	 * @param __newSize the new size
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void resizeImage(File __sourceFile, File __destinationFile, Double __newSize)
			throws IOException;

	/**
	 * Resize image.
	 *
	 * @param __sourceFile the source file
	 * @param __destinationFile the destination file
	 * @param __height the resize height
	 * @param __width the resize width
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void resizeImage(File __sourceFile, File __destinationFile, Double __height,
			Double __width) throws IOException;

	/**
	 * Save file.
	 *
	 * @param __fileStream the file stream
	 * @param __destinationFile the destination file
	 * @param __extension the image extension
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void saveFile(InputStream __fileStream, File __destinationFile, String __extension)
			throws IOException, IllegalArgumentException;

}
