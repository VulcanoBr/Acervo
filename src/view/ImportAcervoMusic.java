package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import model.AcervoMusic;
import model.AcervoMusicItem;

public class ImportAcervoMusic {
	
	private static String arqImp;
	public static  AcervoMusic importDados (String arq)  {
		
		arqImp = arq;
		  
		/* pega o arquiivo do Excel */
				
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(arqImp));
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		/* pega a primeira planilha dentro do arquivo XLS */
		Sheet sheet = workbook.getSheet(0);
		//Pega a quantidade de linhas da planilha 
		int linhas = sheet.getRows();
		//Pega as informaçoes da ultima linha da planilha
		Cell a0 = sheet.getCell(0, (linhas-1));
		Cell a1 = sheet.getCell(1, (linhas-1));
		Cell b2 = sheet.getCell(2, (linhas-1));
        Cell c3 = sheet.getCell(3, (linhas-1));
        Cell e5 = sheet.getCell(5, (linhas-1));
        Cell f6 = sheet.getCell(6, (linhas-1));
        Cell g7 = sheet.getCell(7, (linhas-1));
        Cell l11 = sheet.getCell(11, (linhas-1));
        Cell n13 = sheet.getCell(13, (linhas-1));
        Cell o14 = sheet.getCell(14, (linhas-1));
        Cell p15 = sheet.getCell(15, (linhas-1));
        Cell q16 = sheet.getCell(16, (linhas-1));
        
        AcervoMusic acervoBooKMusic = null ;
        String mediaId = a0.getContents();
        String codigoId = a1.getContents();
        String artista = b2.getContents();
        String titulo = c3.getContents();
        String estilo = e5.getContents();
        String genero  = f6.getContents();
        String anoLancamento2 = g7.getContents();
        Integer anoLancamento = Integer.parseInt((String)anoLancamento2);
        String totalTime = l11.getContents();
        String tempoTotal = totalTime.replace(".", ":");
        String paisLancamento = n13.getContents();
        String patrocinador = o14.getContents();
        String formato = p15.getContents();
        String codigoDeBarras = q16.getContents();
        String tipo = null;
        String codigoCatalogo = null;
        Integer qtdTotal = linhas-1;
        String capa = null;
        Integer edicao = null;
        Date dataAtualizacao = null;
        
        acervoBooKMusic = new AcervoMusic(tipo, codigoCatalogo, artista, titulo, anoLancamento,
				genero, estilo, qtdTotal, tempoTotal, formato,
				patrocinador, paisLancamento, codigoId, mediaId, codigoDeBarras,
				capa, edicao, dataAtualizacao );
        return acervoBooKMusic;
	}

	public static  List<AcervoMusicItem> importDadosItem (String arq) {
		
		arqImp = arq;
		/* pega o arquiivo do Excel */
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(arqImp));
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /* pega a primeira planilha dentro do arquivo XLS */
        Sheet sheet = workbook.getSheet(0);
        //Pega a quantidade de linhas da planilha 
        int linhas = sheet.getRows();
        AcervoMusicItem acervoMusicItem = null ;
		 List<AcervoMusicItem> acervoMusicItens = new ArrayList<AcervoMusicItem>();
        @SuppressWarnings("unused")
        int colunas = sheet.getColumns();
        for (int i = 1; i < linhas; i++) {
            /* pega os valores das células como se numa matriz */
            Cell i8 = sheet.getCell(8, i);  // numero da trilha
            Cell j9 = sheet.getCell(9, i);  // nome da musica
            Cell k10 = sheet.getCell(10, i);  // participante
            Cell m12 = sheet.getCell(12, i);  // tempo de duração da musica
            Cell p15 = sheet.getCell(15, (linhas-1));  // formato da midia
            Cell r17 = sheet.getCell(17, (linhas-1));  // compositores da musica
            /* pega os conteúdos das células */
            String numTrack = i8.getContents();
            String nameMusic = j9.getContents();
            String feutiring = k10.getContents();
            String duration  = m12.getContents();
            String format  = p15.getContents();
            String composer  = r17.getContents();
    
			Integer numSeq  = Integer.parseInt((String)numTrack);    
			String tituloItem  = nameMusic;
			String tempoDuracao = duration;
			String compositor =  composer;
			String participante =  feutiring;
			String formato = format;
			String codigoCatalogo = null;
			String tipo = "Music";
			
          	acervoMusicItem = new AcervoMusicItem(tipo, codigoCatalogo, numSeq, tituloItem, tempoDuracao,
						compositor, participante, formato);
              acervoMusicItens.add(acervoMusicItem);
        }
   
        return acervoMusicItens; 

	}
}
