package org.agjin.java.tutorial.annotation;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

/**
 * http://wiki.codekin.com/index.php/%EC%BB%A4%EC%8A%A4%ED%85%80_Annotation_%EC%82%AC%EC%9A%A9
 */
public class AnnotationTest {
	
	String requestPath = "/Test.do";
	
	@Test
	public void actionTest() throws Exception {
		
		// 특정 패키지의 클래스들을 조사.
		List<String> classNames = FileUtil.findClassesWithPackageName("org.agjin.java.tutorial.annotation");
		
		// 클래스들 조사
		for(String className : classNames) {
			Class<?> clz = Class.forName(className);
			
			// Controller 이라는 어노테이션 체크
//			Annotation controller = clz.getAnnotation(Controller.class);
//			if(controller==null) {
//				System.out.println("-- null-- " + className);
//				continue;
//			}
			if (!clz.isAnnotationPresent(Controller.class)) {
				System.out.println("-- Controller unmatch -- " + className);
				continue;
			}
			
			System.out.println("-- ok --" + className);
			
			// 객체를 생성.
			Object obj = clz.newInstance();
			
			// 메소드를 조사.
			Method[] methods = clz.getMethods();
			for (Method method : methods) {
				RequestMapping requestMappingAnno = method.getAnnotation(RequestMapping.class);
				if(requestMappingAnno==null) {
					continue;
				}
				
				System.out.println("requestMappingAnno.value() ---> " + requestMappingAnno.value());
				if(requestMappingAnno.value().equals(requestPath)) {
					String str = (String)method.invoke(obj, new Object[]{});
					
					System.out.println(str);
					
					
					assertThat(str, equalTo("/Test.jsp"));
				}
			}
			
		}

	}
}
