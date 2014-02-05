package tech.appvalue.newsindia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

public class NewsManager {
	final public static String nlistname="n.nw";
	final public static String dlistname="d.nd";
	final public static String mlistname="m.nm";
	final public static String favlistname = "fav.nf";
	final public static String clistname = "c.nc";
	public static String[] getNewsList(Context context)
	{
		String nstring = getNewsListString(context);
		String[] sl = new String[getCount(nstring)];
		int beg = 0;
		int end = 0;
		for(int i =0;i<sl.length;i++)
		{
			end = nstring.indexOf("#", beg);
			String s = nstring.substring(beg, end);
			sl[i] = s;
			beg = end+1;
		}
		return sl;
	}
	public static String[] getDList(Context context)
	{
		String dstring = getDListString(context);
		String[] sl = new String[getCount(dstring)];
		int beg = 0;
		int end = 0;
		for(int i =0;i<sl.length;i++)
		{
			end = dstring.indexOf("#", beg);
			String s =dstring.substring(beg, end);
			sl[i] = s;
			beg = end+1;
		}
		return sl;
	}
	public static String[] getMList(Context context)
	{
		String mstring = getMListString(context);
		String[] sl = new String[getCount(mstring)];
		int beg = 0;
		int end = 0;
		for(int i =0;i<sl.length;i++)
		{
			end = mstring.indexOf("#", beg);
			String s =mstring.substring(beg, end);
			sl[i] = s;
			beg = end+1;
		}
		return sl;
	}
	public static String[] getFavList(Context context)
	{
		String fvstring = getFavListString(context);
		String[] fvl = new String[getCount(fvstring)];
		int beg = 0;
		int end = 0;
		for(int i =0;i<fvl.length;i++)
		{
			end = fvstring.indexOf("#", beg);
			String s =fvstring.substring(beg, end);
			fvl[i] = s;
			beg = end+1;
		}
		return fvl;
	}
	public static String[] getCList(Context context)
	{
		String cstring = getCListString(context);
		String[] cl = new String[getCount(cstring)];
		int beg = 0;
		int end = 0;
		for(int i =0;i<cl.length;i++)
		{
			end = cstring.indexOf("#", beg);
			String s =cstring.substring(beg, end);
			cl[i] = s;
			beg = end+1;
		}
		return cl;
	}
	public static boolean isFavorite(String name, Context context)
	{
		String s = getFavListString(context);
		int x = s.indexOf(name+"#");
		Log.d("isFavorite",""+x);
		if(x==-1)
			return false;
		return true;
	}
	public static void deleteNewspaper(String name, Context context)
	{
		deleteFromNewsList(name,context);
		deleteFromDList(name,context);
		deleteFromMList(name,context);
		deleteFromFavList(name,context);
	}
	public static String getDOf(String name, Context c)//name is name of newspaper
	{
		String s = getCorresponding(name, getNewsList(c), getDList(c), c);
		return s;
	}
	public static String getCOf(String name, Context c)//name is name of newspaper
	{
		String s = getCorresponding(name, getNewsList(c), getCList(c), c);
		return s;
	}
	public static String getCorresponding(String name, String[] l1, String[] l2, Context c)//name to search in first array
	{//returns value in l2 at position corresponding to position of name in l1
		String l1s = "";
		for(int i =0;i<l1.length;i++)
		{
			l1s = l1s + l1[i] + "#";
		}
		int index = countUpto(name, l1s);
		if(index==-1)
			return "";
		return l2[index];
	}
	public static void deleteFromNewsList(String name, Context context)
	{
		String nstring = getNewsListString(context);
		nstring = nstring.replaceAll(name+"#", "");
		writeNewsList(nstring, context);
	}
	public static void deleteFromDList(String name, Context context)
	{
		String nstring = getDListString(context);
		nstring = nstring.replaceAll(name+"#", "");
		writeDList(nstring, context);
	}
	public static void deleteFromMList(String name, Context context)
	{
		String nstring = getMListString(context);
		nstring = nstring.replaceAll(name+"#", "");
		writeMList(nstring, context);
	}
	public static void deleteFromFavList(String name, Context context)
	{
		String nstring = getFavListString(context);
		nstring = nstring.replaceAll(name+"#", "");
		writeFavList(nstring, context);
	}
	public static void addToNewsList(String name, Context context)
	{
		String nstring = getNewsListString(context);
		nstring=nstring+name+"#";
		writeNewsList(nstring, context);
	}
	public static void addToDList(String name, Context context)
	{
		String nstring = getDListString(context);
		nstring=nstring+name+"#";
		writeDList(nstring, context);
	}
	public static void addToMList(String name, Context context)
	{
		String nstring = getMListString(context);
		nstring=nstring+name+"#";
		writeMList(nstring, context);
	}
	public static void addToFavList(String name, Context context)
	{
		String nstring = getFavListString(context);
		nstring = nstring+name+"#";
		writeFavList(nstring,context);
	}
	public static void addToCList(String name, Context context)
	{
		String nstring = getCListString(context);
		nstring = nstring+name+"#";
		writeCList(nstring,context);
	}
	public static void addNewspaper(String name, String durl, String murl, String type, Context context)
	{

		NewsManager.addToNewsList("Aaj Tak", context);
		NewsManager.addToDList("http://aajtak.intoday.in", context);
		NewsManager.addToCList(type, context);
	}
	public static int getCount(String s)//counts hashes
	{
		int i =0;
		int beg = 0;
		for(;;i++)
		{
			int ind =s.indexOf("#",beg);
			if(ind==-1)
				break;
			beg = ind +1;
		}
		return i;
	}
	public static String getDOfNews(String name, Context context)
	{
		String ns = "";
		String s = getDListString(context);
		String newsS = getNewsListString(context);
		int index = countUpto(name,newsS);//finds index of newspaper in newslist
		String dl[] = getDList(context);
		if(index==-1)
			return "";
		ns = dl[index];
		return s;
	}
	public static int countUpto(String name, String s)//counts hashes upto first instance of name
	{
		String ns = s.substring(0, s.indexOf(name+"#"));
		if(ns.length()==0)//first item
			return 0;
		else
		{
			int x =s.indexOf("#"+name+"#");
			if(x==-1)
				return -1;//not found
			ns = s.substring(0,x);			
		}
		int num = getCount(ns)+1;
		return num;
	}
	public static String[] getInitialNewsList()
	{
		String names[]={"Aaj Tak","Amar Ujala", "Andhra Jyothy", "Andhra Prabha", "Assam Times",
				"Assam Tribune","BBC Hindi","Bollywood Hungama","Business Standard", "CNNGo India", 
				"DNA India", "Dainik Bhaskar","Dainik Jagran", "Dainik Navajyoti","Dinamalar",
				"Dinamani","Divya Bhaskar","ESPNcricinfo","Eenadu","Google News","Greater Kashmir","Gujarat Samachar",
				"Hindustan Times","IBN","IDiva","In.com","India Today","India Express","Kannada Prabha",
				"Lokmat","Loksatta","MSN News India","Malayala Manorama","Mangalam","Mathrubhumi","Mid Day",
				"Mint","Money Control","Mumbai Mirror","NDTV","NDTV Khabar","NewsNow India","One India",
				"One India(Hindi)","One India(Kannada)","One India(Malayalam","One India(Telugu)",
				"Prajavani","Punjab Kesari","Rediff News","Rediff Sports","Reuters","Rising Kashmir","Sakal",
				"Samachar","Samaylive","Sandesh","Sify","Sulekha","Tech2","The Economic Times","The Hindu",
				"The Hindu Business Line","The New Indian Express","The Telegraph","The Times of India",
				"Topix India","Wall Street Journal India","ZeeNews"};
		return names;
	}
	public static void writeNewsList(String string, Context context)
	{
		FileOutputStream os=null;
		try {
			os = context.openFileOutput(nlistname, Context.MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			Log.d("writenewslist","Exception");
			e.printStackTrace();
		}
		try {
			os.write(string.getBytes());
		} catch (IOException e) {
			Log.d("writenewslist","Exception");
			e.printStackTrace();
		}
		try {
			os.close();
		} catch (IOException e) {
			Log.d("writenewslist","Exception");
			e.printStackTrace();
		}
	}
	public static void writeDList(String string, Context context)
	{
		FileOutputStream os=null;
		try {
			os = context.openFileOutput(dlistname, Context.MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			os.write(string.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void writeMList(String string, Context context)
	{
		FileOutputStream os=null;
		try {
			os = context.openFileOutput(mlistname, Context.MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			os.write(string.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void writeFavList(String string, Context context)
	{
		FileOutputStream os=null;
		File mydir = context.getDir("mydir", Context.MODE_PRIVATE); //Creating an internal dir;
		File fileWithinMyDir = new File(mydir, favlistname); //Getting a file within the dir.
		try {
			os=new FileOutputStream(fileWithinMyDir);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			os.write(string.getBytes());
		} catch (IOException e) {
			Log.d("writenewslist","Exception");
			e.printStackTrace();
		}
		try {
			os.close();
		} catch (IOException e) {
			Log.d("writenewslist","Exception");
			e.printStackTrace();
		}
		Log.d("ospath",mydir.getAbsolutePath());
	}
	public static void writeCList(String string, Context context)
	{
		FileOutputStream os=null;
		File mydir = context.getDir("mydir", Context.MODE_PRIVATE); //Creating an internal dir;
		File fileWithinMyDir = new File(mydir, clistname); //Getting a file within the dir.
		try {
			os=new FileOutputStream(fileWithinMyDir);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			os.write(string.getBytes());
		} catch (IOException e) {
			Log.d("writeclist","Exception");
			e.printStackTrace();
		}
		try {
			os.close();
		} catch (IOException e) {
			Log.d("writeclist","Exception");
			e.printStackTrace();
		}
		Log.d("ospathc",mydir.getAbsolutePath());
	}
	public static String getNewsListString(Context context)
	{
		FileInputStream is = getNewsListInputStream(context);
		if(is==null)
			return null;
		String result = readFromFile(is);
		return result;
	}
	public static String getDListString(Context context)
	{
		FileInputStream is = getDListInputStream(context);
		if(is==null)
			return null;
		String result = readFromFile(is);
		return result;
	}
	public static String getMListString(Context context)
	{
		FileInputStream is = getMListInputStream(context);
		if(is==null)
			return null;
		String result = readFromFile(is);
		return result;
	}
	public static String getFavListString(Context context)
	{
		FileInputStream is = getFavListInputStream(context);
		if(is==null)
		{
			Log.d("is","Exception-null");
			return null;
		}
		String result = readFromFile(is);
		return result;
	}
	public static String getCListString(Context context)
	{
		FileInputStream is = getCListInputStream(context);
		if(is==null)
		{
			Log.d("is","Exception-nullc");
			return null;
		}
		String result = readFromFile(is);
		return result;
	}
	public static FileInputStream getNewsListInputStream(Context context)
	{
		FileInputStream is = null;
		try {
			is = context.openFileInput(nlistname);
		} catch (FileNotFoundException e) {
			return null;
		}
		return is;
	}
	public static FileInputStream getDListInputStream(Context context)
	{
		FileInputStream is = null;
		try {
			is = context.openFileInput(dlistname);
		} catch (FileNotFoundException e) {
			return null;
		}
		return is;
	}
	public static FileInputStream getMListInputStream(Context context)
	{
		FileInputStream is = null;
		try {
			is = context.openFileInput(mlistname);
		} catch (FileNotFoundException e) {
			return null;
		}
		return is;
	}
	public static FileInputStream getFavListInputStream(Context context)
	{
		File mydir = context.getDir("mydir", Context.MODE_PRIVATE); //Creating an internal dir;
		File fileWithinMyDir = new File(mydir, favlistname); //Getting a file within the dir.
		FileInputStream in = null;
		try {
			in=new FileInputStream(fileWithinMyDir);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return in;
	}
	public static FileInputStream getCListInputStream(Context context)
	{
		File mydir = context.getDir("mydir", Context.MODE_PRIVATE); //Creating an internal dir;
		File fileWithinMyDir = new File(mydir, clistname); //Getting a file within the dir.
		FileInputStream in = null;
		try {
			in=new FileInputStream(fileWithinMyDir);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return in;
	}
	public static String readFromFile(FileInputStream is)
	{
		String result = "";
		for(;;)
		{
			try {
				int c = is.read();
				if(c==-1)
					break;
				result=result+(char)c;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = result.trim();
		return result;
	}
}
