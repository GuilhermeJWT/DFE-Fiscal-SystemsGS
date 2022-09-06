package br.com.systemsgs;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

class DfeFiscalApplicationTests {

	public static void main(String[] args) throws IOException {
		System.out.println(fileToByte("C:/Users/Guilherme Santos/nfe/certificado_digital.pfx"));
	}

	private static String fileToByte(String caminhoArquivo) throws IOException{
		byte[] fileContent = Files.readAllBytes(new File(caminhoArquivo).toPath());
		return Base64.getEncoder().encodeToString(fileContent);
	}

}
