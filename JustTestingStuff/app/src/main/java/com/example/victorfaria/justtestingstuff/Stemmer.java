package com.example.victorfaria.justtestingstuff;
import java.util.Arrays;
import java.util.HashSet;

public class Stemmer{

	public String wordStemming(String word)
	{
		return algorithm(word);
	}

	private String algorithm(String st)
	{
		st = processNasalidedVowels(st);
		String stem = st;
		String r1 = findR(stem);
		String r2 = findR(r1);
		String rv = findRV(stem);

		stem = step1(stem,r1,r2,rv);

		if(stem.compareTo(st)==0)
			stem = step2(stem,r1,r2,rv);
		else
		{
			r1 = findR(stem);
			r2 = findR(r1);
			rv = findRV(stem);
		}

		if(stem.compareTo(st)!=0)
		{
			r1 = findR(stem);
			r2 = findR(r1);
			rv = findRV(stem);
			stem = step3(stem,r1,r2,rv);
		}
		else
			stem = step4(stem,r1,r2,rv);

		if(stem.compareTo(st)!=0)
		{
			r1 = findR(stem);
			r2 = findR(r1);
			rv = findRV(stem);
		}

		stem = step5(stem,r1,r2,rv);

		stem = deprocessNasalidedVowels(stem);

		return stem;
	}

	private String step1(String st, String r1, String r2, String rv)
	{
		int i;

		for(i = 0; i<suffix1.length; i++)	//Rule 1
			if(r2.endsWith(suffix1[i]))
				return st.substring(0, st.length()-suffix1[i].length());

		for(i = 0; i<suffix2.length; i++)	//Rule 2
			if(r2.endsWith(suffix2[i]))
				return st.substring(0, st.length()-suffix2[i].length())+"log";

		for(i = 0; i<suffix3.length; i++)	//Rule 3
			if(r2.endsWith(suffix3[i]))
				return st.substring(0, st.length()-suffix3[i].length())+"u";

		for(i = 0; i<suffix4.length; i++)	//Rule 4
			if(r2.endsWith(suffix4[i]))
				return st.substring(0, st.length()-suffix4[i].length())+"ente";

		for(i = 0; i<suffix5.length; i++)	//Rule 5
			if(r1.endsWith(suffix5[i]))
			{
				st = st.substring(0, st.length()-suffix5[i].length());
				if(st.endsWith("iv") && r2.endsWith("iv"+suffix5[i]))
				{
					st = st.substring(0,st.length()-2);
					if(st.endsWith("at")&& r2.endsWith("ativ"+suffix5[i]))
						st = st.substring(0,st.length()-2);
				}
				else if(st.endsWith("os") && r2.endsWith("os"+suffix5[i]))
					st = st.substring(0,st.length()-2);
				else if(st.endsWith("ic") && r2.endsWith("ic"+suffix5[i]))
					st = st.substring(0,st.length()-2);
				else if(st.endsWith("ad") && r2.endsWith("ad"+suffix5[i]))
					st = st.substring(0,st.length()-2);
				return st;
			}

		for(i = 0; i<suffix6.length; i++)	//Rule 6
			if(r2.endsWith(suffix6[i]))
			{
				st = st.substring(0, st.length()-suffix6[i].length());
				if(st.endsWith("ante") && r2.endsWith("ante"+suffix6[i]))
					st = st.substring(0,st.length()-4);
				else if(st.endsWith("avel") && r2.endsWith("avel"+suffix6[i]))
					st = st.substring(0,st.length()-4);
				else if(st.endsWith("ível") && r2.endsWith("ível"+suffix6[i]))
					st = st.substring(0,st.length()-4);
				return st;
			}

		for(i = 0; i<suffix7.length; i++)	//Rule 7
			if(r2.endsWith(suffix7[i]))
			{
				st = st.substring(0, st.length()-suffix7[i].length());
				if(st.endsWith("abil") && r2.endsWith("abil"+suffix7[i]))
					st = st.substring(0,st.length()-4);
				else if(st.endsWith("ic") && r2.endsWith("ic"+suffix7[i]))
					st = st.substring(0,st.length()-2);
				else if(st.endsWith("iv") && r2.endsWith("iv"+suffix7[i]))
					st = st.substring(0,st.length()-2);
				return st;
			}

		for(i = 0; i<suffix8.length; i++)	//Rule 8
			if(r2.endsWith(suffix8[i]))
			{
				st = st.substring(0, st.length()-suffix8[i].length());
				if(st.endsWith("at") && r2.endsWith("at"+suffix8[i]))
					st = st.substring(0,st.length()-2);
				return st;
			}

		for(i = 0; i<suffix9.length; i++)	//Rule 9
			if(rv.endsWith(suffix9[i]))
			{
				if(st.endsWith("e"+suffix9[i]))
					st = st.substring(0,st.length()-suffix9[i].length())+"ir";
				return st;
			}
		return st;
	}

	private String step2(String st, String r1, String r2, String rv)
	{
		for(int i = 0; i<suffixv.length; i++)	//Rule 1
			if(rv.endsWith(suffixv[i]))
				return st.substring(0, st.length()-suffixv[i].length());
		return st;
	}

	private String step3(String st, String r1, String r2, String rv)
	{
		if(rv.endsWith("i")&&st.endsWith("ci")) 	//Rule 1
			return st.substring(0, st.length()-1);
		else
			return st;
	}

	private String step4(String st, String r1, String r2, String rv)
	{
		for(int i = 0; i<suffixr.length; i++)	//Rule 1
			if(rv.endsWith(suffixr[i]))
				return st.substring(0,st.length()-suffixr[i].length());
		return st;
	}

	private String step5(String st, String r1, String r2, String rv)
	{
		for(int i = 0; i<suffixf.length; i++)	//Rule 1
			if(rv.endsWith(suffixf[i]))
			{
				st = st.substring(0,st.length()-suffixf[i].length());
				if(st.endsWith("gu") && rv.endsWith("u"+suffixf[i]))
					st = st.substring(0,st.length()-1);
				else if(st.endsWith("ci") && rv.endsWith("i"+suffixf[i]))
					st = st.substring(0,st.length()-1);
				return st;
			}

		if(st.endsWith("ç"))
			st = st.substring(0,st.length()-1)+"c";
		return st;
	}


	private String findR(String st)
	{
		for(int i = 0; i< st.length()-1; i++)
			if(vowels.contains(st.charAt(i)))
				if(!vowels.contains(st.charAt(i+1)))
					return st.substring(i+2);
		return "";
	}

	private String findRV(String st)
	{
		if(st.length()>2)
		{
			if(!vowels.contains(st.charAt(1)))
			{
				for(int i=2; i<st.length()-1; i++)
					if(vowels.contains(st.charAt(i)))
						return st.substring(i+1);
			}
			else if(vowels.contains(st.charAt(0)) && vowels.contains(st.charAt(1)))
			{
				for(int i=2; i<st.length()-1; i++)
					if(!vowels.contains(st.charAt(i)))
						return st.substring(i+1);
			}
			else
				return st.substring(3);
		}
		return "";
	}

	private String processNasalidedVowels(String st)
	{
		st = st.replaceAll("ã", "a~");
		st = st.replaceAll("õ", "o~");
		return st;
	}

	private String deprocessNasalidedVowels(String st)
	{
		st = st.replaceAll("a~", "ã");
		st = st.replaceAll("o~", "õ");
		return st;
	}

	private final HashSet<Character> vowels = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'á', 'é', 'í', 'ó', 'ú', 'â', 'ê', 'ô'));

	private final String suffix1[] = {"amentos", "imentos", "amento", "imento", "adoras", "adores", "ações", "ismos", "istas", "adora", "ação", "antes", "ância", "ezas", "icos", "icas", "ismo", "ável", "ível", "ista", "osos", "osas", "ador", "ante", "eza", "ico", "ica", "oso", "osa"};
	private final String suffix2[] ={"logías", "logía"};
	private final String suffix3[] ={"uciones", "ución"};
	private final String suffix4[] ={"ências", "ência"};
	private final String suffix5[] ={"amente"};
	private final String suffix6[] ={"mente"};
	private final String suffix7[] ={"idades", "idade"};
	private final String suffix8[] ={"ivas", "ivos", "iva", "ivo"};
	private final String suffix9[] ={"iras", "ira"};
	private final String suffixv[] = {"aríamos", "eríamos", "iríamos", "ássemos", "êssemos", "íssemos", "aríeis", "eríeis", "iríeis", "ásseis", "ésseis", "ísseis", "áramos", "éramos", "íramos", "ávamos", "aremos", "eremos", "iremos", "ariam", "eriam", "iriam", "assem", "essem", "issem", "ara~o", "era~o", "ira~o", "arias", "erias", "irias", "ardes", "erdes", "irdes", "asses", "esses", "isses", "astes", "estes", "istes", "áreis", "areis", "éreis", "ereis", "íreis", "ireis", "áveis", "íamos", "armos", "ermos", "irmos", "aria", "eria", "iria", "asse", "esse", "isse", "aste", "este", "iste", "arei", "erei", "irei", "aram", "eram", "iram", "avam", "arem", "erem", "irem", "ando", "endo", "indo", "adas", "idas", "arás", "aras", "erás", "eras", "irás", "avas", "ares", "eres", "ires", "íeis", "ados", "idos", "ámos", "amos", "emos", "imos", "iras", "ada", "ida", "ará", "ara", "erá", "era", "irá", "ava", "iam", "ado", "ido", "ias", "ais", "eis", "ira", "ia", "ei", "am", "em", "ar", "er", "ir", "as", "es", "is", "eu", "iu", "ou"};
	private final String suffixr[] = {"os", "a", "i", "o", "á", "í", "ó"};
	private final String suffixf[] = {"e", "é", "ê"};

}