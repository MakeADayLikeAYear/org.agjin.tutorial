package org.agjin.java.tutorial.annotation;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class FileUtil {
	public static List<String> findClassesWithPackageName(String packageName) throws RuntimeException {
		List<String> classFileNames = new ArrayList<String>();
		
		try {
			URL currentPath = FileUtil.class.getClassLoader().getResource("");
			Enumeration<URL> en = FileUtil.class.getClassLoader().getResources(packageName.replace(".", "/"));
			
			System.out.println(currentPath.getPath());
			System.out.println(en);
			
			while(en.hasMoreElements()) {
				URL url = en.nextElement();
				System.out.println(url.getPath());
				
				File file = new File(url.toURI());
				File[] files = file.listFiles();
				
				for(File f:files) {
					if(f.isFile()) {
						/**
						 * getName = 파일명 + . + 확장자
						 * --> 파일명 추출. 
						 */
						String fileName = f.getName().substring(0, f.getName().lastIndexOf("."));
						System.out.println(fileName);
						classFileNames.add(packageName + "." + fileName);
					}
				}
			}
			
			
		} catch (IOException e){
			throw new RuntimeException(e.getMessage());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		
		
		return classFileNames;
	}
	
	public static void main(String[] args) {
		System.out.println(FileUtil.findClassesWithPackageName("org.agjin.java.tutorial.annotation"));
	}
}
