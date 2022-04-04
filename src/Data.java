import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Data {

	public  void teste(){
		
		//String data1 = "2022-02-25T16:39:38.105-0300";
		String data2 = "2022-03-02T11:30:00.000-0300";
		
		//String[] aData = data1.split("-0300");
		//LocalDateTime ldt = LocalDateTime.parse(aData[0]);
		
		String[] aData2 = data2.split("-0300");
		LocalDateTime ldt2 = LocalDateTime.parse(aData2[0]);
		
		
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm");
//		System.out.println(ldt.format(dtf));
//		
		
		System.out.println("Data formatada: " + ldt2);
		
		LocalDateTime dataHora = ldt2.plusHours(56);
		
		System.out.println("Data com acrescimo 56hrs: " + dataHora);
		
		int finaisSemana = 0;
		LocalDateTime dataFinal = dataHora;
		
		while(dataFinal.isBefore(ldt2)) {
			
			System.out.println(ldt2.plusDays(1).getDayOfWeek());
			if(ldt2.plusDays(1).getDayOfWeek() == DayOfWeek.SATURDAY) {
				
				finaisSemana+=1;
				ldt2 = ldt2.plusDays(1);
				System.out.println(dataHora.plusMinutes(1).getDayOfWeek().toString());
				continue;
				
			}else {
				ldt2 = ldt2.plusDays(1);
			}
			
			
			if(ldt2.isAfter(dataFinal)) {
				dataFinal = dataFinal.plusMinutes(1);
			}else {
				dataFinal = dataFinal.plusDays(1);
			}
					
			
		}
		
		System.out.println("Qtde fim de semana: " + finaisSemana);

		System.out.println("Data final 24 sem fim de semana: "+dataFinal);
		
//		Duration diff = Duration.between(ldt, ldt2);
//		
//		Long minutos = diff.toMinutes();
//		
//		Long minutos80Percent = (minutos * 80) / 100;
//		
//		LocalDateTime NovaData = ldt.plusMinutes(minutos80Percent);
//		
//		System.out.println(NovaData);
//		

	}

}
