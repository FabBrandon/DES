package algorithms;

import java.io.IOException;

public class Subkeys {
	public String SecretKeys;
	public String PlainTexts;
	public int[] PlainTextsArray=new int [64];
	public int[] SecretKeysArray=new int[64];
	public int[] C;
	public int[] D;
	public int[] L;
	public int[] R;
	public int[] E;
	public int[] K=new int[48];
	public static int[]D0Standard=new int[] {63,55,47,39,31,23,15,7,62,54,46,38,30,22,14,6,61,53,45,37,29,21,13,5,28,20,12,4};
	public static int []CStandard=new int[] {14,17,11,24,1,5,3,28,15,6,21,10,23,19,12,4,26,8,16,7,27,20,13,2};
	public static int[]DStandard=new int[] {13,24,3,9,19,27,2,12,23,17,5,20,16,21,11,28,6,25,18,14,22,8,1,4};
	public static int[]IPL=new int[] {58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8};
	public static int[]IPR=new int[] {57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7};
	public static int[]S1=new int[] {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7,0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8,4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0,15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13};
	public static int[]S2=new int[] {15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10,3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5,0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15,13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9};
	public static int[]S3=new int[] {10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8,13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1,13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7,1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12};
	public static int[]S4=new int[] {7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15,13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9,10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4,3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14};
	public static int[]S5=new int[] {2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9,14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6,4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14,11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3};
	public static int[]S6=new int[] {12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11,10,15,4,2,7,13,9,5,6,1,13,14,0,11,3,8,9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6,4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13};
	public static int[]S7=new int[] {4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1,13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6,1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2,6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12};
	public static int[]S8=new int[] {13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7,1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2,7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8,2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11};
	public static int[][]Skey=new int[][] {S1,S2,S3,S4,S5,S6,S7,S8};
	public static int[]PStandard=new int[] {16,7,20,21,29,12,28,17,1,15,23,26,5,18,31,10,2,8,24,14,32,27,3,9,19,13,30,6,22,11,4,25};
	public static int[]IPStandard=new int[] {40,8,48,16,56,24,64,32,39,7,47,15,55,23,63,31,38,6,46,14,54,22,62,30,37,5,45,13,53,21,61,29,36,4,44,12,52,20,60,28,35,3,43,11,51,19,59,27,34,2,42,10,50,18,58,26,33,1,41,9,49,17,57,25};
	Subkeys(String a,String b){
		this.PlainTexts=a;
		this.SecretKeys=b;
		char[] change=PlainTexts.toCharArray();
		char[] Secret=SecretKeys.toCharArray();
		
		//检验位数
		try {
			if(change.length!=16&&Secret.length!=16)throw new ArithmeticException() ;
			}
		catch(ArithmeticException e) {
			System.out.println("输入数据不为16位");
			}
		
		//转化数组
		for(int i=0;i<change.length;i++) {
			try {
				int []temp1= new int[4];
				int[]temp2=new int[4];
			Sixteen2Two(temp1,Sixteen2Ten(change[i]));
			Sixteen2Two(temp2,Sixteen2Ten(Secret[i]));
			for(int j=0;j<4;j++) {
				PlainTextsArray[i*4+j]=temp1[j];
				SecretKeysArray[i*4+j]=temp2[j];
			}
				
			}
			catch(IOException e) {
				System.err.print(e.getMessage());
			}
		}
		change=null;
		
		
	}
	
	public static int trans(int a,int b) {
		return a*16+b;
	}
	
	public static void Sixteen2Two(int a[],int b) {
		/*
		 * a为2进制存储数组，b为10进制显示的16进制数字
		 */
		int i=3;
		while(b!=0) {
			a[i]=Math.floorMod(b,2);
			i--;
			b/=2;
		}
	}
	
	public static int Two2Sixteen(int []a) {
		//char[] standard=new char[] {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		int count=0;
		for (int i=3;i>=0;i--) {
			count+=(int) (a[i]*Math.pow(2,(3-i)));
			
		}
		return count;
	}
	
	public static char Ten2Sixteen(int a) {
		char[] standard=new char[] {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		return standard[a];
	}

	public static int Sixteen2Ten(char a) throws IOException {
		
		if(a>='0'&&a<='9')return (int)(a-'0');
		else if(a>='a'&&a<='f')return (int)(a-'a')+10;
		else throw new IOException("输入错误"); 
	}
	
	public int add(int a,int b) {
		return Math.floorMod(a+b, 2);
	}
	
	public void IP() {
		
		L=new int[32];
		R=new int[32];
		
		System.out.println();
		for(int i=0;i<32;i++) {
			L[i]=PlainTextsArray[IPL[i]-1];
			
			R[i]=PlainTextsArray[IPR[i]-1]; 
		}

	}
	
	public int[] f(int[]a,int[]b){
		//E
		E=new int[48];
		int[] S=new int[32];
		int ten=0;
		int one=0;
		int k=0;
		int count=0;
		for(int i=0;i<32;i++,k++) {
			if(k%4==0) {
				
				E[count++]=a[(Math.floorMod(i-1, 32))];
				
			}
			
			E[count++]=a[i];
			
			if(k%4==3) {
				
				E[count++]=a[(Math.floorMod(i+1, 32))];
			}
			
		}
		
		for(int i=0;i<48;i++) {
			E[i]=add(E[i], b[i]);
		}
		
		for(int i=0;i<8;i++) {
			
			ten=E[6*i+0]*2+E[6*i+5];
			one=E[6*i+1]*8+E[6*i+2]*4+E[6*i+3]*2+E[6*i+4];
			
			int [] temp=new int[4];
			Sixteen2Two(temp,Skey[i][trans(ten,one)]);
			
			for(int j=0;j<4;j++) {
				
				S[i*4+j]=temp[j];
			}
	
		}
		//置换输出
		int []F =new int[32];
		for(int i=0;i<32;i++) {
			
			F[i]=S[PStandard[i]-1];
		}
		   return F;
	}
	
	
	public void Check(int[] temp) {
		int sum=0;
		for(int i=0;i<7;i++) {
			sum+=temp[i];
		}
		if(Math.floorMod(sum, 2)!=temp[7])throw new ArithmeticException("");
	}
	
	public void IPMinus1() {
		int [] IP1=new int[64];
		for (int i=0;i<32;i++) {
		
			IP1[i]=R[i];
			IP1[32+i]=L[i];
		}
	
		int[] Result=new int[64];
		for (int i=0;i<64;i++) {
			Result[i]=IP1[IPStandard[i]-1];
		}
	
		for(int j=0;j<16;j++) {
			int []temp=new int[4];
			for(int i=0;i<4;i++) {
				temp[i]=Result[j*4+i];
			//System.out.print(temp[i]);
			}		
		System.out.print(Ten2Sixteen(Two2Sixteen(temp))+" ");
		}
	}


	public void Checkout() {
		//检验奇偶校验位
		for (int i=0;i<8;i++) {
			int [] temp=new int[8];
			for (int j=0;j<8;j++) {
				temp[j]=SecretKeysArray[i*8+j];
			}
			try {
			Check(temp);
			}
			catch(ArithmeticException e) {
				System.out.println("未通过奇偶校验");
			}
		}
	}
	
	public void ReplacementChosing1() {
		//置换选择1
		C=new int[28];
		for(int i=0;i<28;i++) {
			C[i]=SecretKeysArray[Math.floorMod(57-i*8-1,65)];
			
		}
		
		D=new int[28];
		for(int i=0;i<28;i++) {
			D[i]=SecretKeysArray[D0Standard[i]-1];
			
		}
	}
	
	public void ReplacementChosing2() {
		//置换运算2
		for(int j=0;j<24;j++) {
			
			K[j]=C[CStandard[j]-1];
			K[j+24]=D[DStandard[j]-1];
		}
		//获得密钥
//		for(int i=0;i<48;i++) {
//			System.out.print(K[i]+" ");
//		}
//		System.out.println();
	}
	
	public void PlainTextsCircle() {
		int[]LR=new int[32];
		for(int j=0;j<32;j++) {
			LR[j]=R[j];
		}
		
		
		int[] temp=new int[32];
		for(int j=0;j<32;j++) {
			temp[j]=f(R,K)[j];
		}
		
		for(int j=0;j<32;j++) {		
			R[j]=add(L[j], temp[j]);
		}
			
		for(int j=0;j<32;j++) {
			L[j]=LR[j];
		}
	}
	
	public void ShiftLeft() {
		int tempintC=C[0];
		for(int j=0;j<C.length-1;j++) C[j]=C[j+1];
		C[C.length-1]=tempintC;
		
		int tempintD=D[0];
		for(int j=0;j<C.length-1;j++) D[j]=D[j+1];
		D[D.length-1]=tempintD;
	}
	
	public void Encipher() {
		
		//Checkout();
		//置换选择1
		ReplacementChosing1();
		//明文
		IP();
		
		for (int i=0;i<16;i++) {
			//循环左移
			int times=2;
			if(i==0||i==1||i==8||i==15)times--;
			for(;times>0;times--) {
				ShiftLeft();
			}
			
			//置换运算2
			ReplacementChosing2();
			//明文处理
			PlainTextsCircle();
		}
		//IP-1
		IPMinus1();
	}
		
	public static void main(String[]args) {
		Subkeys a=new Subkeys("0123456789abcdef","133457799bbcdff1");
		a.Encipher();
	}
}
