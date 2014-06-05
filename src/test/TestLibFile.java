package test;

import java.io.File;

public class TestLibFile {

	public static void main(String[] args) {
		File file = new File("WebContent/WEB-INF/lib");  
        for (File f : file.listFiles()) {  
            System.out.println(" " +"lib/"+ f.getName() + " ");  
        }  
	}
}
