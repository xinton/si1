package lab01;
import java.util.Scanner;
public class Sistema {

	/*****
	 * @param args
	 */
		private static Escritor wter;
	public static void main(String[] args) throws Exception{
		Scanner entrada = new Scanner(System.in);
		wter = new Escritor();
		System.out.println("Informe um número");
		
		String entry = entrada.next();
		try{
			wter.setInput(entry);
			System.out.println(wter.geraNumero());
		}catch(Exception e){
			System.out.println(e.getMessage());
			main(args);
		}
		
	}

}
