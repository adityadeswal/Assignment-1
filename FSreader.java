package lol;
import java.io.*;
import java.util.StringTokenizer;

public class FSreader 
{
  public static String read(String path)
 {   
	  StringBuilder s= new StringBuilder("");
    try
   {   
	     FileInputStream f1 = new FileInputStream("C:/Users/hp/Downloads/Flat File System/FI.txt");
	     FileInputStream f2 = new FileInputStream("C:/Users/hp/Downloads/Flat File System/FS.txt");
	     byte[] data = new byte[48];
	     String R[]= new String[20];//Can increase no of strings according to file but max=8192
	     int c=0;
	     byte[][] FSdata = new byte[8192][128];
		 int k;
		 StringTokenizer st = new StringTokenizer(path,"/");
		 String lname="";
		 String dname="";
		   
	      while(st.hasMoreTokens()){
	    	  dname=lname;
	    	  lname=st.nextToken();}
	      //System.out.println(dname+" "+lname);
	  
	      for( k=0;k<8192;k++)	f2.read(FSdata[k],0,128);
				
	 while((f1.read(data,0,48))!=-1)
    {        int j;
			 int d=0;
			 int x=0;
			 int a=0;
			 for( j=0;j<32;j++) if(data[j]==0)break;
			 String name = new String(data,0,j);
			 R[c++]=name;
			 
	  if(lname.equals(name))
	 {
		//type of file 
			 if(data[33]<0){
				a=data[33]+256; 
				 }
			 else a=data[33];
			 if(a==0){}
			 else 
				 if(a==128){}
				 else if(a==160){}
			 
		//directory entry
			 if(data[34]==0&&data[35]==0)//root directory{}
	         if(data[34]<0){ 
	           d=(data[34]+255)*256;}
			else d=(data[34]-1)*256;
			if(data[35]<0)
				d=d+data[35]+255;
			else d=d+data[35]-1;			
			 
if(R[d].equals(dname))
		
{
		//size	
				int size=0;
				int n=0;
				for(j=36;j<40;j++){
					if(data[j]<0){
						size=size*256+(data[j]+256);
					}
					else
						size=size*256+data[j];
					
				}
				System.out.println(size);
				if(size>512){
					
				n=(size-512)/126;	
				//System.out.println("###"+size+" "+n+"###");
					
				}
				
			//blocks
				for(j=40;j<48;j++)
				{
					
					if(data[j]!=0){					
					if(j%2==0){						
						if(data[j]<0)x=256*(data[j]+256);
						else x=256*data[j];
					          }
					else {
						if(data[j]<0){x=x+data[j]+256;}
						else x=x+data[j];
						if(j==47&&n!=0){s.append(new String(FSdata[x]),0,126);}
						else s.append(new String(FSdata[x]));
						 }
					              }
				}
				
				int y=0;
				
				while(n!=0)
				{
   				if(FSdata[x][126]<0)	
					y=(FSdata[x][126]+256)*256;
				else
					y=(FSdata[x][126])*256;
				if(FSdata[x][127]<0)	
					y=y+FSdata[x][127]+256;
				else
					y=y+FSdata[x][127];	
				if(n==1){s.append(new String(FSdata[y]));}
				else 
					s.append(new String(FSdata[y],0,126));
				n--;
				x=y;
				}
				s.append(new String(FSdata[y+1]).trim());
				
				
}
			 				 
		} 
     }
	 f1.close();
	 f2.close();
	               
    }catch(Exception e){System.out.println(e);}   
             if(s.equals("")){
            	 return null;
             } return s.toString();
		
 }
	
 public static void main(String args[]){
		
		System.out.println(FSreader.read("Progress/Fuse/Fuse.txt"));
		            
                                        }	
}