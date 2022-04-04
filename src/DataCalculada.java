import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class DataCalculada {
	
	public static HashMap<String,LocalDateTime> listaFeriados(){
		
		HashMap<String,LocalDateTime> lista = new HashMap<>();
		lista.put("1-Carnaval",LocalDateTime.parse("2022-02-28T00:00:00.000"));
		lista.put("2-Carnaval",LocalDateTime.parse("2022-03-01T00:00:00.000"));
		lista.put("3-Carnaval",LocalDateTime.parse("2022-03-02T00:00:00.000"));
		lista.put("Sexta-feira Santa",LocalDateTime.parse("2022-04-15T00:00:00.000"));
		lista.put("Tiradente",LocalDateTime.parse("2022-04-21T00:00:00.000"));
		lista.put("Feriado 08/04",LocalDateTime.parse("2022-04-22T00:00:00.000"));
		lista.put("Corpus Christis",LocalDateTime.parse("2022-06-16T00:00:00.000"));
		lista.put("1-Banco de horas",LocalDateTime.parse("2022-06-17T00:00:00.000"));
		lista.put("Independencia",LocalDateTime.parse("2022-09-07T00:00:00.000"));
		lista.put("Nossa Senhora Aparecida",LocalDateTime.parse("2022-10-12T00:00:00.000"));
		lista.put("Dia de Finados",LocalDateTime.parse("2022-11-02T00:00:00.000"));
		lista.put("2-Banco de horas",LocalDateTime.parse("2022-11-14T00:00:00.000"));
		lista.put("Proclamação da República",LocalDateTime.parse("2022-11-15T00:00:00.000"));
		
		return lista;
		
	}
	
	
	public static void main(String[] args) {
		
//		LocalDateTime dt1 = LocalDateTime.now();
//		for (int i = 0; i < 28; i++) {
			rotina();
//		}
//		LocalDateTime dt2 = LocalDateTime.now();
//		Duration d = Duration.between(dt1, dt2);
//		System.out.println(d.getSeconds()); ;
		
			
	}
	
	
	public static Boolean ValidaSabadoDomingo(LocalDateTime dataHora) {
		return (dataHora.getDayOfWeek() == DayOfWeek.SATURDAY || 
				dataHora.getDayOfWeek() == DayOfWeek.SUNDAY);
	}
	
	
	public static Boolean ValidaFeriados(LocalDateTime dataHoraAuxiliar) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");
		String dataHoraAuxiliarString = dtf.format(dataHoraAuxiliar);
		HashMap<String, LocalDateTime> lista = listaFeriados();
		
		//Verifica se é feriado
		for(Map.Entry<String, LocalDateTime> dataHora : lista.entrySet()) {
			
			String dataHoraString = dtf.format(dataHora.getValue());
			
			
			if(dataHoraAuxiliarString.equals(dataHoraString)) {
			
			    return true;			
			}
		}
		
		return false;
		
	}
	
	public static LocalDateTime zerar(LocalDateTime dataHora) {
		
		while(dataHora.getMinute()!=0) {
			dataHora = dataHora.minusMinutes(1);
		}
		
		return dataHora;
	}
	
	public static boolean isHorarioComercial(LocalDateTime dataHoraAuxiliar) {
		
		if(dataHoraAuxiliar.getDayOfWeek() == DayOfWeek.FRIDAY) {
			
			if(dataHoraAuxiliar.getHour() >= 8 && dataHoraAuxiliar.getHour() < 17) {
				
					return true;	
				
			}
			
		}else
		if(dataHoraAuxiliar.getHour() >= 8 && dataHoraAuxiliar.getHour() < 18) {
			
		
				return true;	
			
		}
		
		
		return false;
	}
	
	public static void rotina() {
	
		final LocalDateTime dataHoraInicial = LocalDateTime.parse("2022-03-04T08:19:00.000");
		int HorasSLA = 56 * 60;
		final LocalDateTime dataHoraFinal  = dataHoraInicial.plusMinutes(HorasSLA);
		
		System.out.println("Data/hora inicial: "+dataHoraInicial);
		System.out.println("Data/Hora final  : "+dataHoraFinal);
		
		LocalDateTime dataHoraAuxiliar = dataHoraInicial;
		
		int horaAdicionadas = 1;
		int qtdeMinutos = 0;
		
		final int Porcent40 =  (40 * (56*60))/100;
		final int Porcent60 =  (60 * (56*60))/100;
		LocalDateTime dataHoraPercent40 = null;
		LocalDateTime dataHoraPercent60 = null;

		while(horaAdicionadas <= HorasSLA) {
				
			qtdeMinutos+=1;
			if(ValidaSabadoDomingo(dataHoraAuxiliar) || ValidaFeriados(dataHoraAuxiliar)) {
				dataHoraAuxiliar = zerar(dataHoraAuxiliar);
				dataHoraAuxiliar = dataHoraAuxiliar.plusDays(1);
				continue;
			}
			
			dataHoraAuxiliar = dataHoraAuxiliar.plusMinutes(1);
			
			if(isHorarioComercial(dataHoraAuxiliar)) {
					horaAdicionadas +=1;
					if(horaAdicionadas == Porcent40) {
						dataHoraPercent40 = dataHoraAuxiliar;
					}
					if(horaAdicionadas == Porcent60) {
						dataHoraPercent60 = dataHoraAuxiliar;
					}
			}
		}
		
		System.out.println("contador: "+qtdeMinutos);
		System.out.println("Horas adicionadas: "+horaAdicionadas);
		System.out.println("Data/Hora final: " +dataHoraAuxiliar);
		System.out.println("40% do tempo: "+ dataHoraPercent40);
		System.out.println("60% do tempo: "+ dataHoraPercent60);
		
	}
	

}
