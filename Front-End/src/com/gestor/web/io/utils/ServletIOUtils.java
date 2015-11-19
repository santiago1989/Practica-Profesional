package com.gestor.web.io.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;

import com.gestor.common.util.FileUtils;
import com.gestor.common.util.Utils;
import com.gestor.entidades.Adjunto;
import com.gestor.entidades.Incidencia;
import com.gestor.web.utils.Constants;

public class ServletIOUtils {

	private static final String RELATIVE_URL = "\\tickets\\";
	
	public static List<Adjunto> writeFiles(List<FileItem> fileList,Incidencia incidencia) throws IOException{
		List<Adjunto> list = new ArrayList<Adjunto>(fileList.size());
		for (FileItem file : fileList) {
			list.add(writeFile(file,incidencia));
		}
		return list;
	}
	
	public static Adjunto writeFile(FileItem file,Incidencia incidencia) throws IOException{
		String relativePath = RELATIVE_URL.concat(Utils.normalize(file.getName()));
		String absolutePath = Constants.BASE_DIR.concat(relativePath);
		File ioFile = FileUtils.buildFile(absolutePath);
		OutputStream output = new FileOutputStream(ioFile);
		InputStream input = file.getInputStream();
		IOUtils.copy(input, output);
		return new Adjunto(absolutePath, incidencia);
	}
}
