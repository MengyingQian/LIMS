package com.ddxq.common.MD5;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class MD5 
{
    static final int S11 = 7;
    static final int S12 = 12;
    static final int S13 = 17;
    static final int S14 = 22;

    static final int S21 = 5;
    static final int S22 = 9;
    static final int S23 = 14;
    static final int S24 = 20;

    static final int S31 = 4;
    static final int S32 = 11;
    static final int S33 = 16;
    static final int S34 = 23;

    static final int S41 = 6;
    static final int S42 = 10;
    static final int S43 = 15;
    static final int S44 = 21;

    static final byte[] PADDING = { -128, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    private long[] STATE 	= new long[4];  // STATE (ABCD)
    private long[] COUNT 	= new long[2];  // number of bits, modulo 2^64 (lsb first)
    private byte[] BUFFER 	= new byte[64]; // input BUFFER

    private byte[] DIGEST_BYTES 	= new byte[16];

    public MD5() 
    {
        md5Init();
    }

    private void md5Init() 
    {
        COUNT[0] = 0L;
        COUNT[1] = 0L;
        ///* Load magic initialization constants.
        STATE[0] = 0x67452301L;
        STATE[1] = 0xefcdab89L;
        STATE[2] = 0x98badcfeL;
        STATE[3] = 0x10325476L;
        return;
    }
 
    private long F(long x, long y, long z) {
        return (x & y) | ((~x) & z);
    }
    private long G(long x, long y, long z) {
        return (x & z) | (y & (~z));
    }
    private long H(long x, long y, long z) {
        return x ^ y ^ z;
    }
    private long I(long x, long y, long z) {
        return y ^ (x | (~z));
    }

    private long FF(long a, long b, long c, long d, long x, long s, long ac) 
    {
        a += F (b, c, d) + x + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }
    private long GG(long a, long b, long c, long d, long x, long s, long ac) 
    {
        a += G (b, c, d) + x + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }
    private long HH(long a, long b, long c, long d, long x, long s, long ac) 
    {
        a += H (b, c, d) + x + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }
    private long II(long a, long b, long c, long d, long x, long s, long ac) 
    {
        a += I (b, c, d) + x + ac;
        a = ((int) a << s) | ((int) a >>> (32 - s));
        a += b;
        return a;
    }

    private void md5Update(byte[] inbuf, int inputLen) 
    {
        int i, index, partLen;
        byte[] block = new byte[64];
        index = (int)(COUNT[0] >>> 3) & 0x3F;
        // /* Update number of bits */
        if ((COUNT[0] += (inputLen << 3)) < (inputLen << 3))
            COUNT[1]++;
        COUNT[1] += (inputLen >>> 29);

        partLen = 64 - index;

        // Transform as many times as possible.
        if (inputLen >= partLen) 
        {
            md5Memcpy(BUFFER, inbuf, index, 0, partLen);
            md5Transform(BUFFER);
            for (i = partLen; i + 63 < inputLen; i += 64) 
            {
                md5Memcpy(block, inbuf, 0, i, 64);
                md5Transform (block);
            }
            index = 0;

        } else
            i = 0;
	    ///* Buffer remaining input */
        md5Memcpy(BUFFER, inbuf, index, i, inputLen - i);
    }

    private void md5Memcpy (byte[] output, byte[] input, int outpos, int inpos, int len)
    {
        int i;
        for (i = 0; i < len; i++)
            output[outpos + i] = input[inpos + i];
    }

    private void md5Transform (byte block[]) 
    {
        long a = STATE[0], b = STATE[1], c = STATE[2], d = STATE[3];
        long[] x = new long[16];

        Decode (x, block, 64);

        /* Round 1 */
        a = FF (a, b, c, d, x[0],  S11, 0xd76aa478L); /* 1 */
        d = FF (d, a, b, c, x[1],  S12, 0xe8c7b756L); /* 2 */
        c = FF (c, d, a, b, x[2],  S13, 0x242070dbL); /* 3 */
        b = FF (b, c, d, a, x[3],  S14, 0xc1bdceeeL); /* 4 */
        a = FF (a, b, c, d, x[4],  S11, 0xf57c0fafL); /* 5 */
        d = FF (d, a, b, c, x[5],  S12, 0x4787c62aL); /* 6 */
        c = FF (c, d, a, b, x[6],  S13, 0xa8304613L); /* 7 */
        b = FF (b, c, d, a, x[7],  S14, 0xfd469501L); /* 8 */
        a = FF (a, b, c, d, x[8],  S11, 0x698098d8L); /* 9 */
        d = FF (d, a, b, c, x[9],  S12, 0x8b44f7afL); /* 10 */
        c = FF (c, d, a, b, x[10], S13, 0xffff5bb1L); /* 11 */
        b = FF (b, c, d, a, x[11], S14, 0x895cd7beL); /* 12 */
        a = FF (a, b, c, d, x[12], S11, 0x6b901122L); /* 13 */
        d = FF (d, a, b, c, x[13], S12, 0xfd987193L); /* 14 */
        c = FF (c, d, a, b, x[14], S13, 0xa679438eL); /* 15 */
        b = FF (b, c, d, a, x[15], S14, 0x49b40821L); /* 16 */

        /* Round 2 */
        a = GG (a, b, c, d, x[1],  S21, 0xf61e2562L); /* 17 */
        d = GG (d, a, b, c, x[6],  S22, 0xc040b340L); /* 18 */
        c = GG (c, d, a, b, x[11], S23, 0x265e5a51L); /* 19 */
        b = GG (b, c, d, a, x[0],  S24, 0xe9b6c7aaL); /* 20 */
        a = GG (a, b, c, d, x[5],  S21, 0xd62f105dL); /* 21 */
        d = GG (d, a, b, c, x[10], S22, 0x2441453L);  /* 22 */
        c = GG (c, d, a, b, x[15], S23, 0xd8a1e681L); /* 23 */
        b = GG (b, c, d, a, x[4],  S24, 0xe7d3fbc8L); /* 24 */
        a = GG (a, b, c, d, x[9],  S21, 0x21e1cde6L); /* 25 */
        d = GG (d, a, b, c, x[14], S22, 0xc33707d6L); /* 26 */
        c = GG (c, d, a, b, x[3],  S23, 0xf4d50d87L); /* 27 */
        b = GG (b, c, d, a, x[8],  S24, 0x455a14edL); /* 28 */
        a = GG (a, b, c, d, x[13], S21, 0xa9e3e905L); /* 29 */
        d = GG (d, a, b, c, x[2],  S22, 0xfcefa3f8L); /* 30 */
        c = GG (c, d, a, b, x[7],  S23, 0x676f02d9L); /* 31 */
        b = GG (b, c, d, a, x[12], S24, 0x8d2a4c8aL); /* 32 */

        /* Round 3 */
        a = HH (a, b, c, d, x[5],  S31, 0xfffa3942L); /* 33 */
        d = HH (d, a, b, c, x[8],  S32, 0x8771f681L); /* 34 */
        c = HH (c, d, a, b, x[11], S33, 0x6d9d6122L); /* 35 */
        b = HH (b, c, d, a, x[14], S34, 0xfde5380cL); /* 36 */
        a = HH (a, b, c, d, x[1],  S31, 0xa4beea44L); /* 37 */
        d = HH (d, a, b, c, x[4],  S32, 0x4bdecfa9L); /* 38 */
        c = HH (c, d, a, b, x[7],  S33, 0xf6bb4b60L); /* 39 */
        b = HH (b, c, d, a, x[10], S34, 0xbebfbc70L); /* 40 */
        a = HH (a, b, c, d, x[13], S31, 0x289b7ec6L); /* 41 */
        d = HH (d, a, b, c, x[0],  S32, 0xeaa127faL); /* 42 */
        c = HH (c, d, a, b, x[3],  S33, 0xd4ef3085L); /* 43 */
        b = HH (b, c, d, a, x[6],  S34, 0x4881d05L);  /* 44 */
        a = HH (a, b, c, d, x[9],  S31, 0xd9d4d039L); /* 45 */
        d = HH (d, a, b, c, x[12], S32, 0xe6db99e5L); /* 46 */
        c = HH (c, d, a, b, x[15], S33, 0x1fa27cf8L); /* 47 */
        b = HH (b, c, d, a, x[2],  S34, 0xc4ac5665L); /* 48 */

        /* Round 4 */
        a = II (a, b, c, d, x[0],  S41, 0xf4292244L); /* 49 */
        d = II (d, a, b, c, x[7],  S42, 0x432aff97L); /* 50 */
        c = II (c, d, a, b, x[14], S43, 0xab9423a7L); /* 51 */
        b = II (b, c, d, a, x[5],  S44, 0xfc93a039L); /* 52 */
        a = II (a, b, c, d, x[12], S41, 0x655b59c3L); /* 53 */
        d = II (d, a, b, c, x[3],  S42, 0x8f0ccc92L); /* 54 */
        c = II (c, d, a, b, x[10], S43, 0xffeff47dL); /* 55 */
        b = II (b, c, d, a, x[1],  S44, 0x85845dd1L); /* 56 */
        a = II (a, b, c, d, x[8],  S41, 0x6fa87e4fL); /* 57 */
        d = II (d, a, b, c, x[15], S42, 0xfe2ce6e0L); /* 58 */
        c = II (c, d, a, b, x[6],  S43, 0xa3014314L); /* 59 */
        b = II (b, c, d, a, x[13], S44, 0x4e0811a1L); /* 60 */
        a = II (a, b, c, d, x[4],  S41, 0xf7537e82L); /* 61 */
        d = II (d, a, b, c, x[11], S42, 0xbd3af235L); /* 62 */
        c = II (c, d, a, b, x[2],  S43, 0x2ad7d2bbL); /* 63 */
        b = II (b, c, d, a, x[9],  S44, 0xeb86d391L); /* 64 */

        STATE[0] += a;
        STATE[1] += b;
        STATE[2] += c;
        STATE[3] += d;

    }

    private void Encode (byte[] output, long[] input, int len) 
    {
        int i, j;
        for (i = 0, j = 0; j < len; i++, j += 4) 
        {
            output[j] = (byte)(input[i] & 0xffL);
            output[j + 1] = (byte)((input[i] >>> 8) & 0xffL);
            output[j + 2] = (byte)((input[i] >>> 16) & 0xffL);
            output[j + 3] = (byte)((input[i] >>> 24) & 0xffL);
        }
    }

    private void Decode (long[] output, byte[] input, int len) 
    {
        int i, j;
        for (i = 0, j = 0; j < len; i++, j += 4)
            output[i] = b2iu(input[j]) |
            (b2iu(input[j + 1]) << 8) |
            (b2iu(input[j + 2]) << 16) |
            (b2iu(input[j + 3]) << 24);
        return;
    }
    private static long b2iu(byte b) {
        return b < 0 ? b & 0x7F + 128 : b;
    }

    private static String byteHEX(byte ib) 
    {
        char[] Digit = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
        char[] ob = new char[2];
        ob[0] = Digit[ (ib >>> 4)& 0X0F ];
        ob[1] = Digit[ ib & 0X0F ];
        String s = new String(ob);
        return s;
    }

    private void md5Final () 
    {
        byte[] bits = new byte[8];
        int index, padLen;
        ///* Save number of bits */
        Encode (bits, COUNT, 8);
        ///* Pad out to 56 mod 64.
        index = (int)(COUNT[0] >>> 3) & 0x3f;
        padLen = (index < 56) ? (56 - index) : (120 - index);
        md5Update (PADDING, padLen);
        ///* Append length (before padding) */
        md5Update(bits, 8);
        ///* Store state in DIGEST_BYTES */
        Encode (DIGEST_BYTES, STATE, 16);
		
    }

    public byte[] getMD5Bytes(String inbuf) 
    {
        md5Init();
        md5Update(inbuf.getBytes(), inbuf.length());
        md5Final();
        return DIGEST_BYTES;
    }

    public byte getMD5Byte(String inbuf, int pos) 
    {
        md5Init();
        md5Update(inbuf.getBytes(), inbuf.length());
        md5Final();
		if( (pos>=0)&&(pos<16) )
        	return DIGEST_BYTES[pos];
        else
        	return 0;    
    }

    public byte[] getRevisedMD5Bytes(String inbuf) 
    {
        md5Init();
        md5Update(inbuf.getBytes(), inbuf.length());
        md5Final();
        byte[] rtn = new byte[8];

        rtn[0] = (byte)(DIGEST_BYTES[0] & 0x7F);
        
        for(int i=1; i<8; i++) 
        	rtn[i] = DIGEST_BYTES[i];
        
        return rtn;
    }

    public byte getRevisedMD5Byte(String inbuf, int pos) 
    {
        md5Init();
        md5Update(inbuf.getBytes(), inbuf.length());
        md5Final();
        if (pos==0)
        	return (byte)(DIGEST_BYTES[0] & 0x7F);
        else if( (pos>0)&&(pos<8) )
        	return DIGEST_BYTES[pos];
        else
        	return 0;
    }

    public String getMD5HexString(String inbuf) 
    {
        md5Init();
        md5Update(inbuf.getBytes(), inbuf.length());
        md5Final();
        String strDigestHex = "";
        for (int i = 0; i < 16; i++) {
            strDigestHex += byteHEX(DIGEST_BYTES[i]);
        }
        return strDigestHex;
    }

    public String getRevisedMD5HexString(String inbuf) 
    {
        byte[] bts = new byte[8];
        bts = this.getRevisedMD5Bytes(inbuf);
        String strRevisedMD5 ="";
        for (int i = 0; i < 8; i++) 
            strRevisedMD5 += byteHEX(bts[i]);

        return strRevisedMD5;
    }

	//This is the most useful method:  
	//e.g: inbuf:  "http://tiger.www.net.cn"
	//     output: 3206200698051426249
    public Long getRevisedMD5Long(String inbuf) 
    {
        String strRevisedMD5 = this.getRevisedMD5HexString(inbuf);
       	long rtn=0;
       	try{
       		rtn = Long.parseLong(strRevisedMD5,16);
       	}catch(Exception e){}
       	
       	return rtn;        
    }

	// return a string which consists of  19 number chars:
    public String getRevisedChecksumForURN(String url) 
    {
        String strTmp = this.getRevisedMD5Long(url).toString();
        int len = strTmp.length();
        for( int i=0; i<19-len; i++)
        	strTmp = "0" + strTmp;
       	return strTmp;        
    }
    
    public String getRevisedChecksumForURN(URL u) 
    {
        String strTmp = this.getRevisedMD5Long(u.toString()).toString();
        int len = strTmp.length();
        for( int i=0; i<19-len; i++)
        	strTmp = "0" + strTmp;
       	return strTmp;        
    }


    public String getSavingPath(long num, int level) 
    {
		String path = "";

        String strBin = Long.toBinaryString(num);
        
        String strTmp = strBin;
        
        for (int i=0; i<(63-strBin.length()); i++)
         	strTmp = "0" + strTmp;

        strTmp = "1" + strTmp;  		// the first bit must be '1'
        
		if ( (level<0)||(level>16) )  	// if (level==0) 	path = "";
			level =1;  					// level: 1~16

        for (int j=1; j<=level; j++)
        {
        	String tmp = strTmp.substring(0, (10+j) );
        	
        	path += Integer.parseInt(tmp,2) + File.separator;
        }
        
        return path;
    }

    public String getSavingPath(String term)  {
    	try {
			String tmp = URLEncoder.encode(term.toLowerCase(), "UTF-8");
			long  rmd5 = getRevisedMD5Long(tmp);
			
			String path =  getSavingPath( rmd5, 3 );

	        String strTmp = "" + rmd5;
		    int len = strTmp.length();
	        for( int i=0; i<19-len; i++)
	        	strTmp = "0" + strTmp;

			path = path + strTmp;
			
	        return path;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} 
		
    }


    public static void main(String args[]) 
    {
		long d1 = new java.util.Date().getTime();
		System.out.println(d1); 
        MD5 m = new MD5();

		String term = "0";
		//term = "123";
		System.out.println("Term:\t\t"+ term);
		System.out.println("MD5:\t\t" + m.getMD5HexString(term) );
		System.out.println("MD5RL:\t\t" + m.getRevisedMD5Long(term) );
		System.out.println("SPath:\t\t" + m.getSavingPath(term) );
		
		long d2 = new java.util.Date().getTime();
		System.out.println(d2); 
		System.out.println( (d2-d1) + "ms"); 

    }

}
