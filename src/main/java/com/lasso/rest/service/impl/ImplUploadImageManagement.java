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
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.lasso.rest.service.UploadImageManagement;
import com.lasso.util.EncryptionUtil;

@Service
public class ImplUploadImageManagement implements UploadImageManagement {

	@Override
	public String generateImageName() {
		return "/" + EncryptionUtil.uniqid("", false)
		        + new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date()) + ".jpg";
	}

	@Override
	public void addWatermark(File __sourceImageFile, File __watermarkImageFile,
	        ImageOutputStream __destinationImageFile) throws IOException {
		BufferedImage _sourceImage = ImageIO.read(__sourceImageFile);
		BufferedImage _watermarkImage = ImageIO.read(__watermarkImageFile);

		// initializes necessary graphic properties
		Graphics2D _g2d = (Graphics2D) _sourceImage.getGraphics();
		AlphaComposite _alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
		_g2d.setComposite(_alphaChannel);

		// calculates the coordinate where the image is painted
		int _topLeftX = (_sourceImage.getWidth() - _watermarkImage.getWidth()) / 2;
		int _topLeftY = (_sourceImage.getHeight() - _watermarkImage.getHeight()) / 2;

		// paints the image watermark
		_g2d.drawImage(_watermarkImage, _topLeftX, _topLeftY, null);

		ImageIO.write(_sourceImage, "jpg", __destinationImageFile);
		_g2d.dispose();

	}

	@Override
	public void saveFile(InputStream __fileStream, File __destinationFile)
	        throws IOException, IllegalArgumentException {
		BufferedImage _buffered = ImageIO.read(__fileStream);
		if (_buffered == null) {
			throw new IllegalArgumentException("File not image");
		}
		ImageIO.write(_buffered, "jpg", __destinationFile);
	}

	@Override
	public void resizeImage(File __sourceFile, File __destinationFile, Double __height,
	        Double __width) throws IOException {
		if (__sourceFile.isFile()) {
			Image image = ImageIO.read(__sourceFile);
			BufferedImage sbi = (BufferedImage) image;
			/* Scale this image */
			BufferedImage dbi = null;
			if (sbi != null) {
				dbi = new BufferedImage(__width.intValue(), __height.intValue(), sbi.getType());
				Graphics2D g = dbi.createGraphics();
				g.drawImage(sbi, 0, 0, __width.intValue(), __height.intValue(), null);
				g.dispose();
			}
			/* retrieve image */
			ImageIO.write(dbi, "jpg", __destinationFile);
		}
	}

	@Override
	public void resizeImage(File __sourceFile, File __destinationFile, Double __newSize)
	        throws IOException {
		if (__sourceFile.isFile()) {
			Image image = ImageIO.read(__sourceFile);
			BufferedImage sbi = (BufferedImage) image;
			/* Get size of image */
			Double width = (double) sbi.getWidth();
			Double height = (double) sbi.getHeight();
			Double d;
			/* Get proportion of scaled image */
			if (__newSize > height && __newSize > width) {
				FileUtils.copyFile(__sourceFile, __destinationFile);
			}
			else {
				if (width > height) {
					d = width / height;
					height = __newSize;
					width = height * d;
				}
				else {
					d = height / width;
					width = __newSize;
					height = width * d;
				}
				/* Scale this image */
				BufferedImage dbi = null;
				if (sbi != null) {
					dbi = new BufferedImage(width.intValue(), height.intValue(), sbi.getType());
					Graphics2D g = dbi.createGraphics();
					g.drawImage(sbi, 0, 0, width.intValue(), height.intValue(), null);
					g.dispose();
				}
				/* retrieve image */
				ImageIO.write(dbi, "jpg", __destinationFile);
			}
		}
	}

}
