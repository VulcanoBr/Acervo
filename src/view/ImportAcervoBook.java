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
import model.AcervoBook;
import model.AcervoBookItem;

public class ImportAcervoBook {

	private static String arqImp;
	public static  AcervoBook importDados (String arq)  {
		
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
		Cell b1 = sheet.getCell(1, (linhas-1));
		Cell c2 = sheet.getCell(2, (linhas-1));
        Cell d3 = sheet.getCell(3, (linhas-1));
        Cell e4 = sheet.getCell(4, (linhas-1));
        Cell f5 = sheet.getCell(5, (linhas-1));
        Cell g6 = sheet.getCell(6, (linhas-1));
        Cell h7 = sheet.getCell(7, (linhas-1));
        Cell i8 = sheet.getCell(8, (linhas-1));
        Cell j9 = sheet.getCell(9, (linhas-1));
        Cell k10 = sheet.getCell(10, (linhas-1));
        Cell l11 = sheet.getCell(11, (linhas-1));
        Cell m12 = sheet.getCell(12, (linhas-1));
        Cell n13 = sheet.getCell(13, (linhas-1));
        Cell o14 = sheet.getCell(14, (linhas-1));
        Cell p15 = sheet.getCell(15, (linhas-1));
        Cell q16 = sheet.getCell(16, (linhas-1));
        Cell r17 = sheet.getCell(17, (linhas-1));
        Cell s18 = sheet.getCell(18, (linhas-1));
        Cell t19 = sheet.getCell(19, (linhas-1));
        
        AcervoBook acervoBooK = null ;
        String artista = a0.getContents();;
        String titulo = b1.getContents();
		String tituloOriginal = c2.getContents();;
		String tradutor = d3.getContents();
		String anoPrimEdicao = e4.getContents();
		Integer anoPrimeiraEdicao = Integer.parseInt(anoPrimEdicao);
		String anoLanc = f5.getContents();
		Integer anoLancamento = Integer.parseInt(anoLanc);
		String edicao2 = g6.getContents();
		Integer edicao = Integer.parseInt(edicao2);
		String totalPage = h7.getContents();
		Integer qtdPage = Integer.parseInt(totalPage);
		String linguagem = i8.getContents();
		String genero = j9.getContents();
		String categoria = k10.getContents();
		String formato = l11.getContents();
		String patrocinador = m12.getContents();
		String paisLancamento = n13.getContents();
		String codigoDeBarras = o14.getContents();
		String codigoCDD = p15.getContents();
		String codigoCDU = q16.getContents();
		String capa = "";
		String sinopse = r17.getContents();
		String series = s18.getContents();
		String observacao = t19.getContents();;
		
        String tipo = null;
        String codigoCatalogo = null;
        Date dtAtualizacao = null;
        
        acervoBooK =  new AcervoBook(tipo, codigoCatalogo, artista,titulo, tituloOriginal, 
				 tradutor, anoPrimeiraEdicao,anoLancamento,  edicao,
				 linguagem, genero, categoria, formato, qtdPage,  
				 patrocinador, paisLancamento, codigoDeBarras, codigoCDD,
				 codigoCDU, capa, sinopse, series, observacao, dtAtualizacao);
        return acervoBooK;
	}

	public static  List<AcervoBookItem> importDadosItem (String arq) {
		
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
        AcervoBookItem acervoBookItem = null ;
		 List<AcervoBookItem> acervoBookItens = new ArrayList<AcervoBookItem>();
        @SuppressWarnings("unused")
        int colunas = sheet.getColumns();
        for (int i = 1; i < linhas; i++) {
            /* pega os valores das células como   numa matriz */
            Cell u20 = sheet.getCell(20, i);
            Cell v21 = sheet.getCell(21, i);
            Cell w22 = sheet.getCell(22, i);
            Cell x23 = sheet.getCell(23, i);
           
            /* pega os conteúdos das células */
            String numCapitulo2 = u20.getContents();
            Integer numCapitulo = Integer.parseInt(numCapitulo2);
            String descCapitulo = v21.getContents();
            String numPage2 = w22.getContents();
            Integer numPage = Integer.parseInt(numPage2);
            String totalPage  = x23.getContents();
            Integer qtdPage = Integer.parseInt(totalPage);
    
			String formato = null;
			String codigoCatalogo = null;
			String tipo = "Book";
			
          	acervoBookItem = new AcervoBookItem(tipo, codigoCatalogo, numCapitulo, descCapitulo,
          						 numPage, qtdPage, formato);
              acervoBookItens.add(acervoBookItem);
        }
   
        return acervoBookItens; 

	}
}
