package com.lasso.rest.service.impl;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

import com.lasso.rest.service.UploadImageManagement;
import com.lasso.util.EncryptionUtil;

/**
 * The Class ImplUploadImageManagement.
 *
 * @author Paul Mai
 */
@Service
public class ImplUploadImageManagement implements UploadImageManagement {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.UploadImageManagement#addWatermark(java.io.File, java.io.File,
	 * javax.imageio.stream.ImageOutputStream)
	 */
	@Override
	public void addWatermark(File __sourceImageFile, File __watermarkImageFile,
			File __destinationImageFile) throws IOException {
		Logger.getLogger(this.getClass())
		.debug("Add watermark for image: " + __destinationImageFile.getAbsolutePath());
		BufferedImage _sourceImage = ImageIO.read(__sourceImageFile);
		BufferedImage _watermarkImage = ImageIO.read(__watermarkImageFile);
		File _resizeWatermark = File.createTempFile("temp", null);
		this.resizeImage(__watermarkImageFile, _resizeWatermark, _watermarkImage.getHeight() / 2,
				_watermarkImage.getWidth() / 2);

		BufferedImage _resizeWatermarkImage = ImageIO.read(_resizeWatermark);

		// initializes necessary graphic properties
		Graphics2D _g2d = (Graphics2D) _sourceImage.getGraphics();
		AlphaComposite _alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
		_g2d.setComposite(_alphaChannel);

		// calculates the coordinate where the image is painted
		int _topLeftX = (_sourceImage.getWidth() - _resizeWatermarkImage.getWidth()) / 2;
		int _topLeftY = (_sourceImage.getHeight() - _resizeWatermarkImage.getHeight()) / 2;

		// paints the image watermark
		_g2d.drawImage(_resizeWatermarkImage, _topLeftX, _topLeftY, null);

		String _imageName = __sourceImageFile.getName();
		String _fileExtension = _imageName.substring(_imageName.lastIndexOf(".") + 1,
				_imageName.length());
		ImageIO.write(_sourceImage, _fileExtension, __destinationImageFile);
		this.changeOwner(__destinationImageFile);

		_g2d.dispose();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.UploadImageManagement#copyImage(java.io.File, java.io.File)
	 */
	@Override
	public void copyImage(File __sourceFile, File __destinationFile) throws IOException {
		FileUtils.copyFileToDirectory(__sourceFile, __destinationFile, false);
		this.changeOwner(__destinationFile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.UploadImageManagement#generateImageName(java.lang.String)
	 */
	@Override
	public String generateImageName(String __extension) {
		return EncryptionUtil.uniqid("", false)
				+ new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date()) + "." + __extension;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.UploadImageManagement#resizeImage(java.io.File, java.io.File,
	 * java.lang.Integer)
	 */
	@Override
	public void resizeImage(File __sourceFile, File __destinationFile, Integer __newSize)
			throws IOException {
		Logger.getLogger(this.getClass())
		.debug("Destination path of image: " + __destinationFile.getAbsolutePath());
		if (__sourceFile.isFile()) {
			Image image = ImageIO.read(__sourceFile);
			BufferedImage sbi = (BufferedImage) image;

			/* Scale this image */
			BufferedImage dbi = null;
			if (sbi != null) {
				try {
					dbi = Scalr.resize(sbi, __newSize);
				}
				finally {
					sbi.flush();
				}
			}
			/* retrieve image */
			ImageIO.write(dbi, "jpg", __destinationFile);
			this.changeOwner(__destinationFile);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.UploadImageManagement#resizeImage(java.io.File, java.io.File,
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void resizeImage(File __sourceFile, File __destinationFile, Integer __height,
			Integer __width) throws IOException {
		Logger.getLogger(this.getClass())
		.debug("Destination path of image: " + __destinationFile.getAbsolutePath());
		if (__sourceFile.isFile()) {
			Image image = ImageIO.read(__sourceFile);
			BufferedImage sbi = (BufferedImage) image;
			/* Scale this image */
			BufferedImage dbi = null;
			if (sbi != null) {
				try {
					dbi = Scalr.resize(sbi, __width, __height);
				}
				finally {
					sbi.flush();
				}

			}
			/* retrieve image */
			ImageIO.write(dbi, "jpg", __destinationFile);
			this.changeOwner(__destinationFile);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lasso.rest.service.UploadImageManagement#saveFile(java.io.InputStream, java.io.File,
	 * java.lang.String)
	 */
	@Override
	public void saveFile(InputStream __fileStream, File __destinationFile, String __extension)
			throws IOException, IllegalArgumentException {
		Logger.getLogger(this.getClass())
		.debug("Destination path of image: " + __destinationFile.getAbsolutePath());
		BufferedImage _buffered = ImageIO.read(__fileStream);
		if (_buffered == null) {
			throw new IllegalArgumentException("File not image");
		}
		ImageIO.write(_buffered, __extension, __destinationFile);
		this.changeOwner(__destinationFile);
	}

	/**
	 * Change owner.
	 *
	 * @param __file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void changeOwner(File __file) throws IOException {
		__file.setExecutable(true, false);
		__file.setReadable(true, false);
	}
}
