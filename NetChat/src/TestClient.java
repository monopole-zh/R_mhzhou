import java.io.File;

public class TestClient {
	public static void main(String[] args) throws Exception {
		File file = new  File("image");
		File [] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i].getPath());
		}
	}

}
