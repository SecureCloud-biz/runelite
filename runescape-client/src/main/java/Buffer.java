import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("fb")
@Implements("Buffer")
public class Buffer extends Node {
   @ObfuscatedName("f")
   @Export("crc32Table")
   static int[] crc32Table;
   @ObfuscatedName("x")
   @Export("crc64Table")
   static long[] crc64Table;
   @ObfuscatedName("j")
   @Export("payload")
   public byte[] payload;
   @ObfuscatedName("h")
   @ObfuscatedGetter(
      intValue = -1770376623
   )
   @Export("offset")
   public int offset;

   static {
      crc32Table = new int[256];

      int var2;
      for(int var1 = 0; var1 < 256; ++var1) {
         int var0 = var1;

         for(var2 = 0; var2 < 8; ++var2) {
            if((var0 & 1) == 1) {
               var0 = var0 >>> 1 ^ -306674912;
            } else {
               var0 >>>= 1;
            }
         }

         crc32Table[var1] = var0;
      }

      crc64Table = new long[256];

      for(var2 = 0; var2 < 256; ++var2) {
         long var4 = (long)var2;

         for(int var3 = 0; var3 < 8; ++var3) {
            if((var4 & 1L) == 1L) {
               var4 = var4 >>> 1 ^ -3932672073523589310L;
            } else {
               var4 >>>= 1;
            }
         }

         crc64Table[var2] = var4;
      }

   }

   public Buffer(int var1) {
      this.payload = class175.method3385(var1);
      this.offset = 0;
   }

   public Buffer(byte[] var1) {
      this.payload = var1;
      this.offset = 0;
   }

   @ObfuscatedName("an")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "-32938062"
   )
   @Export("readInt")
   public int readInt() {
      this.offset += 4;
      return ((this.payload[this.offset - 3] & 255) << 16) + (this.payload[this.offset - 1] & 255) + ((this.payload[this.offset - 2] & 255) << 8) + ((this.payload[this.offset - 4] & 255) << 24);
   }

   @ObfuscatedName("h")
   @ObfuscatedSignature(
      signature = "(II)V",
      garbageValue = "-1708916116"
   )
   @Export("putByte")
   public void putByte(int var1) {
      this.payload[++this.offset - 1] = (byte)var1;
   }

   @ObfuscatedName("u")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "1581656769"
   )
   @Export("readUnsignedByte")
   public int readUnsignedByte() {
      return this.payload[++this.offset - 1] & 255;
   }

   @ObfuscatedName("au")
   @ObfuscatedSignature(
      signature = "(B)I",
      garbageValue = "-115"
   )
   public int method3189() {
      if(this.payload[this.offset] < 0) {
         return this.readInt() & Integer.MAX_VALUE;
      } else {
         int var1 = this.readUnsignedShort();
         return var1 == 32767?-1:var1;
      }
   }

   @ObfuscatedName("ao")
   @ObfuscatedSignature(
      signature = "(B)I",
      garbageValue = "0"
   )
   @Export("read24BitInt")
   public int read24BitInt() {
      this.offset += 3;
      return ((this.payload[this.offset - 3] & 255) << 16) + (this.payload[this.offset - 1] & 255) + ((this.payload[this.offset - 2] & 255) << 8);
   }

   @ObfuscatedName("az")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "-1437491906"
   )
   @Export("readVarInt")
   public int readVarInt() {
      byte var1 = this.payload[++this.offset - 1];

      int var2;
      for(var2 = 0; var1 < 0; var1 = this.payload[++this.offset - 1]) {
         var2 = (var2 | var1 & 127) << 7;
      }

      return var2 | var1;
   }

   @ObfuscatedName("av")
   @ObfuscatedSignature(
      signature = "(B)Ljava/lang/String;",
      garbageValue = "8"
   )
   @Export("readString")
   public String readString() {
      int var1 = this.offset;

      while(this.payload[++this.offset - 1] != 0) {
         ;
      }

      int var2 = this.offset - var1 - 1;
      return var2 == 0?"":ScriptEvent.getString(this.payload, var1, var2);
   }

   @ObfuscatedName("n")
   @ObfuscatedSignature(
      signature = "(I)B",
      garbageValue = "254092860"
   )
   @Export("readByte")
   public byte readByte() {
      return this.payload[++this.offset - 1];
   }

   @ObfuscatedName("d")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "3600000"
   )
   @Export("readUnsignedShort")
   public int readUnsignedShort() {
      this.offset += 2;
      return (this.payload[this.offset - 1] & 255) + ((this.payload[this.offset - 2] & 255) << 8);
   }

   @ObfuscatedName("ae")
   @ObfuscatedSignature(
      signature = "([BIIS)V",
      garbageValue = "352"
   )
   @Export("readBytes")
   public void readBytes(byte[] var1, int var2, int var3) {
      for(int var4 = var2; var4 < var3 + var2; ++var4) {
         var1[var4] = this.payload[++this.offset - 1];
      }

   }

   @ObfuscatedName("x")
   @ObfuscatedSignature(
      signature = "(II)V",
      garbageValue = "-214229758"
   )
   @Export("putInt")
   public void putInt(int var1) {
      this.payload[++this.offset - 1] = (byte)(var1 >> 24);
      this.payload[++this.offset - 1] = (byte)(var1 >> 16);
      this.payload[++this.offset - 1] = (byte)(var1 >> 8);
      this.payload[++this.offset - 1] = (byte)var1;
   }

   @ObfuscatedName("f")
   @ObfuscatedSignature(
      signature = "(II)V",
      garbageValue = "1035494529"
   )
   @Export("putShort")
   public void putShort(int var1) {
      this.payload[++this.offset - 1] = (byte)(var1 >> 8);
      this.payload[++this.offset - 1] = (byte)var1;
   }

   @ObfuscatedName("ax")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "-202358804"
   )
   @Export("readShort")
   public int readShort() {
      this.offset += 2;
      int var1 = (this.payload[this.offset - 1] & 255) + ((this.payload[this.offset - 2] & 255) << 8);
      if(var1 > 32767) {
         var1 -= 65536;
      }

      return var1;
   }

   @ObfuscatedName("ab")
   @ObfuscatedSignature(
      signature = "(I)Ljava/lang/String;",
      garbageValue = "2073269136"
   )
   @Export("getJagString")
   public String getJagString() {
      byte var1 = this.payload[++this.offset - 1];
      if(var1 != 0) {
         throw new IllegalStateException("");
      } else {
         int var2 = this.offset;

         while(this.payload[++this.offset - 1] != 0) {
            ;
         }

         int var3 = this.offset - var2 - 1;
         return var3 == 0?"":ScriptEvent.getString(this.payload, var2, var3);
      }
   }

   @ObfuscatedName("y")
   @ObfuscatedSignature(
      signature = "(II)V",
      garbageValue = "-1375415281"
   )
   @Export("putVarInt")
   public void putVarInt(int var1) {
      if((var1 & -128) != 0) {
         if((var1 & -16384) != 0) {
            if((var1 & -2097152) != 0) {
               if((var1 & -268435456) != 0) {
                  this.putByte(var1 >>> 28 | 128);
               }

               this.putByte(var1 >>> 21 | 128);
            }

            this.putByte(var1 >>> 14 | 128);
         }

         this.putByte(var1 >>> 7 | 128);
      }

      this.putByte(var1 & 127);
   }

   @ObfuscatedName("m")
   @ObfuscatedSignature(
      signature = "([BIIB)V",
      garbageValue = "101"
   )
   @Export("putBytes")
   public void putBytes(byte[] var1, int var2, int var3) {
      for(int var4 = var2; var4 < var3 + var2; ++var4) {
         this.payload[++this.offset - 1] = var1[var4];
      }

   }

   @ObfuscatedName("ar")
   @ObfuscatedSignature(
      signature = "(I)J",
      garbageValue = "-222534711"
   )
   @Export("readLong")
   public long readLong() {
      long var1 = (long)this.readInt() & 4294967295L;
      long var3 = (long)this.readInt() & 4294967295L;
      return var3 + (var1 << 32);
   }

   @ObfuscatedName("ap")
   @ObfuscatedSignature(
      signature = "([IIII)V",
      garbageValue = "-1748954202"
   )
   @Export("decryptXtea")
   public void decryptXtea(int[] var1, int var2, int var3) {
      int var4 = this.offset;
      this.offset = var2;
      int var5 = (var3 - var2) / 8;

      for(int var6 = 0; var6 < var5; ++var6) {
         int var7 = this.readInt();
         int var8 = this.readInt();
         int var9 = -957401312;
         int var10 = -1640531527;

         for(int var11 = 32; var11-- > 0; var7 -= var8 + (var8 << 4 ^ var8 >>> 5) ^ var9 + var1[var9 & 3]) {
            var8 -= var7 + (var7 << 4 ^ var7 >>> 5) ^ var1[var9 >>> 11 & 3] + var9;
            var9 -= var10;
         }

         this.offset -= 8;
         this.putInt(var7);
         this.putInt(var8);
      }

      this.offset = var4;
   }

   @ObfuscatedName("af")
   @ObfuscatedSignature(
      signature = "(B)I",
      garbageValue = "82"
   )
   @Export("getLargeSmart")
   public int getLargeSmart() {
      return this.payload[this.offset] < 0?this.readInt() & Integer.MAX_VALUE:this.readUnsignedShort();
   }

   @ObfuscatedName("w")
   @ObfuscatedSignature(
      signature = "(Ljava/lang/String;B)V",
      garbageValue = "63"
   )
   @Export("putString")
   public void putString(String var1) {
      int var2 = var1.indexOf(0);
      if(var2 >= 0) {
         throw new IllegalArgumentException("");
      } else {
         this.offset += CombatInfo1.method1552(var1, 0, var1.length(), this.payload, this.offset);
         this.payload[++this.offset - 1] = 0;
      }
   }

   @ObfuscatedName("p")
   @ObfuscatedSignature(
      signature = "(II)V",
      garbageValue = "-2117702166"
   )
   @Export("put24bitInt")
   public void put24bitInt(int var1) {
      this.payload[++this.offset - 1] = (byte)(var1 >> 16);
      this.payload[++this.offset - 1] = (byte)(var1 >> 8);
      this.payload[++this.offset - 1] = (byte)var1;
   }

   @ObfuscatedName("at")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "944577852"
   )
   @Export("readShortSmart")
   public int readShortSmart() {
      int var1 = this.payload[this.offset] & 255;
      return var1 < 128?this.readUnsignedByte() - 64:this.readUnsignedShort() - '쀀';
   }

   @ObfuscatedName("b")
   @ObfuscatedSignature(
      signature = "(Ljava/lang/String;I)V",
      garbageValue = "2081679174"
   )
   @Export("putJagString")
   public void putJagString(String var1) {
      int var2 = var1.indexOf(0);
      if(var2 >= 0) {
         throw new IllegalArgumentException("");
      } else {
         this.payload[++this.offset - 1] = 0;
         this.offset += CombatInfo1.method1552(var1, 0, var1.length(), this.payload, this.offset);
         this.payload[++this.offset - 1] = 0;
      }
   }

   @ObfuscatedName("v")
   @ObfuscatedSignature(
      signature = "(II)V",
      garbageValue = "2000988706"
   )
   @Export("putLength")
   public void putLength(int var1) {
      this.payload[this.offset - var1 - 1] = (byte)var1;
   }

   @ObfuscatedName("aa")
   @ObfuscatedSignature(
      signature = "(IB)I",
      garbageValue = "-36"
   )
   @Export("putCrc")
   public int putCrc(int var1) {
      int var2 = CollisionData.method3074(this.payload, var1, this.offset);
      this.putInt(var2);
      return var2;
   }

   @ObfuscatedName("bc")
   @ObfuscatedSignature(
      signature = "(II)V",
      garbageValue = "-1274013666"
   )
   public void method3208(int var1) {
      this.payload[++this.offset - 1] = (byte)(var1 + 128);
      this.payload[++this.offset - 1] = (byte)(var1 >> 8);
   }

   @ObfuscatedName("as")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "1583218954"
   )
   @Export("getUSmart")
   public int getUSmart() {
      int var1 = this.payload[this.offset] & 255;
      return var1 < 128?this.readUnsignedByte():this.readUnsignedShort() - '耀';
   }

   @ObfuscatedName("c")
   @Export("putLong")
   public void putLong(long var1) {
      this.payload[++this.offset - 1] = (byte)((int)(var1 >> 56));
      this.payload[++this.offset - 1] = (byte)((int)(var1 >> 48));
      this.payload[++this.offset - 1] = (byte)((int)(var1 >> 40));
      this.payload[++this.offset - 1] = (byte)((int)(var1 >> 32));
      this.payload[++this.offset - 1] = (byte)((int)(var1 >> 24));
      this.payload[++this.offset - 1] = (byte)((int)(var1 >> 16));
      this.payload[++this.offset - 1] = (byte)((int)(var1 >> 8));
      this.payload[++this.offset - 1] = (byte)((int)var1);
   }

   @ObfuscatedName("by")
   @ObfuscatedSignature(
      signature = "(II)V",
      garbageValue = "1359575154"
   )
   public void method3312(int var1) {
      this.payload[++this.offset - 1] = (byte)(var1 >> 8);
      this.payload[++this.offset - 1] = (byte)(var1 + 128);
   }

   @ObfuscatedName("ba")
   @ObfuscatedSignature(
      signature = "(B)B",
      garbageValue = "0"
   )
   public byte method3345() {
      return (byte)(128 - this.payload[++this.offset - 1]);
   }

   @ObfuscatedName("bh")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "-1767744388"
   )
   public int method3209() {
      this.offset += 2;
      return ((this.payload[this.offset - 1] & 255) << 8) + (this.payload[this.offset - 2] & 255);
   }

   @ObfuscatedName("bt")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "-2034964042"
   )
   public int method3351() {
      this.offset += 4;
      return ((this.payload[this.offset - 1] & 255) << 8) + ((this.payload[this.offset - 4] & 255) << 16) + (this.payload[this.offset - 2] & 255) + ((this.payload[this.offset - 3] & 255) << 24);
   }

   @ObfuscatedName("ac")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "803482757"
   )
   public int method3201() {
      return this.payload[++this.offset - 1] - 128 & 255;
   }

   @ObfuscatedName("bk")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "1606117212"
   )
   public int method3185() {
      return 128 - this.payload[++this.offset - 1] & 255;
   }

   @ObfuscatedName("be")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "-1085493227"
   )
   public int method3218() {
      this.offset += 4;
      return (this.payload[this.offset - 4] & 255) + ((this.payload[this.offset - 3] & 255) << 8) + ((this.payload[this.offset - 2] & 255) << 16) + ((this.payload[this.offset - 1] & 255) << 24);
   }

   @ObfuscatedName("bq")
   @ObfuscatedSignature(
      signature = "(II)V",
      garbageValue = "949861724"
   )
   @Export("putLEShortA")
   public void putLEShortA(int var1) {
      this.payload[++this.offset - 1] = (byte)(var1 >> 16);
      this.payload[++this.offset - 1] = (byte)(var1 >> 24);
      this.payload[++this.offset - 1] = (byte)var1;
      this.payload[++this.offset - 1] = (byte)(var1 >> 8);
   }

   @ObfuscatedName("bn")
   @ObfuscatedSignature(
      signature = "(II)V",
      garbageValue = "1586190136"
   )
   @Export("putShortLE")
   public void putShortLE(int var1) {
      this.payload[++this.offset - 1] = (byte)var1;
      this.payload[++this.offset - 1] = (byte)(var1 >> 8);
   }

   @ObfuscatedName("bl")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "-2067301108"
   )
   public int method3219() {
      this.offset += 4;
      return ((this.payload[this.offset - 2] & 255) << 24) + ((this.payload[this.offset - 4] & 255) << 8) + (this.payload[this.offset - 3] & 255) + ((this.payload[this.offset - 1] & 255) << 16);
   }

   @ObfuscatedName("bj")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "-1520581900"
   )
   public int method3329() {
      this.offset += 2;
      return ((this.payload[this.offset - 1] & 255) << 8) + (this.payload[this.offset - 2] - 128 & 255);
   }

   @ObfuscatedName("bw")
   @ObfuscatedSignature(
      signature = "(B)I",
      garbageValue = "-95"
   )
   public int method3210() {
      this.offset += 2;
      return (this.payload[this.offset - 1] - 128 & 255) + ((this.payload[this.offset - 2] & 255) << 8);
   }

   @ObfuscatedName("bb")
   @ObfuscatedSignature(
      signature = "(II)V",
      garbageValue = "403073143"
   )
   public void method3215(int var1) {
      this.payload[++this.offset - 1] = (byte)var1;
      this.payload[++this.offset - 1] = (byte)(var1 >> 8);
      this.payload[++this.offset - 1] = (byte)(var1 >> 16);
      this.payload[++this.offset - 1] = (byte)(var1 >> 24);
   }

   @ObfuscatedName("a")
   @ObfuscatedSignature(
      signature = "(IB)V",
      garbageValue = "-63"
   )
   @Export("putShortLength")
   public void putShortLength(int var1) {
      this.payload[this.offset - var1 - 2] = (byte)(var1 >> 8);
      this.payload[this.offset - var1 - 1] = (byte)var1;
   }

   @ObfuscatedName("bg")
   @ObfuscatedSignature(
      signature = "(S)I",
      garbageValue = "14111"
   )
   public int method3337() {
      this.offset += 3;
      return (this.payload[this.offset - 3] & 255) + ((this.payload[this.offset - 2] & 255) << 8) + ((this.payload[this.offset - 1] & 255) << 16);
   }

   @ObfuscatedName("ad")
   @ObfuscatedSignature(
      signature = "(Ljava/math/BigInteger;Ljava/math/BigInteger;B)V",
      garbageValue = "-32"
   )
   @Export("encryptRsa")
   public void encryptRsa(BigInteger var1, BigInteger var2) {
      int var3 = this.offset;
      this.offset = 0;
      byte[] var4 = new byte[var3];
      this.readBytes(var4, 0, var3);
      BigInteger var5 = new BigInteger(var4);
      BigInteger var6 = var5.modPow(var1, var2);
      byte[] var7 = var6.toByteArray();
      this.offset = 0;
      this.putShort(var7.length);
      this.putBytes(var7, 0, var7.length);
   }

   @ObfuscatedName("aj")
   @ObfuscatedSignature(
      signature = "(B)Ljava/lang/String;",
      garbageValue = "7"
   )
   @Export("getCESU8")
   public String getCESU8() {
      byte var1 = this.payload[++this.offset - 1];
      if(var1 != 0) {
         throw new IllegalStateException("");
      } else {
         int var2 = this.readVarInt();
         if(var2 + this.offset > this.payload.length) {
            throw new IllegalStateException("");
         } else {
            byte[] var4 = this.payload;
            int var5 = this.offset;
            char[] var6 = new char[var2];
            int var7 = 0;
            int var8 = var5;

            int var11;
            for(int var9 = var2 + var5; var8 < var9; var6[var7++] = (char)var11) {
               int var10 = var4[var8++] & 255;
               if(var10 < 128) {
                  if(var10 == 0) {
                     var11 = '�';
                  } else {
                     var11 = var10;
                  }
               } else if(var10 < 192) {
                  var11 = '�';
               } else if(var10 < 224) {
                  if(var8 < var9 && (var4[var8] & 192) == 128) {
                     var11 = (var10 & 31) << 6 | var4[var8++] & 63;
                     if(var11 < 128) {
                        var11 = '�';
                     }
                  } else {
                     var11 = '�';
                  }
               } else if(var10 < 240) {
                  if(var8 + 1 < var9 && (var4[var8] & 192) == 128 && 128 == (var4[var8 + 1] & 192)) {
                     var11 = (var10 & 15) << 12 | (var4[var8++] & 63) << 6 | var4[var8++] & 63;
                     if(var11 < 2048) {
                        var11 = '�';
                     }
                  } else {
                     var11 = '�';
                  }
               } else if(var10 < 248) {
                  if(var8 + 2 < var9 && (var4[var8] & 192) == 128 && (var4[var8 + 1] & 192) == 128 && (var4[var8 + 2] & 192) == 128) {
                     var11 = (var10 & 7) << 18 | (var4[var8++] & 63) << 12 | (var4[var8++] & 63) << 6 | var4[var8++] & 63;
                     if(var11 >= 65536 && var11 <= 1114111) {
                        var11 = '�';
                     } else {
                        var11 = '�';
                     }
                  } else {
                     var11 = '�';
                  }
               } else {
                  var11 = '�';
               }
            }

            String var3 = new String(var6, 0, var7);
            this.offset += var2;
            return var3;
         }
      }
   }

   @ObfuscatedName("ay")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "-1896360759"
   )
   @Export("readUnsignedShortOb1")
   public int readUnsignedShortOb1() {
      return 0 - this.payload[++this.offset - 1] & 255;
   }

   @ObfuscatedName("o")
   @ObfuscatedSignature(
      signature = "(Ljava/lang/CharSequence;B)V",
      garbageValue = "32"
   )
   @Export("putCESU8")
   public void putCESU8(CharSequence var1) {
      int var2 = class12.method71(var1);
      this.payload[++this.offset - 1] = 0;
      this.putVarInt(var2);
      this.offset += ScriptState.method1120(this.payload, this.offset, var1);
   }

   @ObfuscatedName("g")
   public void method3163(long var1) {
      this.payload[++this.offset - 1] = (byte)((int)(var1 >> 40));
      this.payload[++this.offset - 1] = (byte)((int)(var1 >> 32));
      this.payload[++this.offset - 1] = (byte)((int)(var1 >> 24));
      this.payload[++this.offset - 1] = (byte)((int)(var1 >> 16));
      this.payload[++this.offset - 1] = (byte)((int)(var1 >> 8));
      this.payload[++this.offset - 1] = (byte)((int)var1);
   }

   @ObfuscatedName("bu")
   @ObfuscatedSignature(
      signature = "(B)I",
      garbageValue = "0"
   )
   public int method3367() {
      this.offset += 2;
      int var1 = ((this.payload[this.offset - 1] & 255) << 8) + (this.payload[this.offset - 2] - 128 & 255);
      if(var1 > 32767) {
         var1 -= 65536;
      }

      return var1;
   }

   @ObfuscatedName("bp")
   @ObfuscatedSignature(
      signature = "(I)I",
      garbageValue = "2030206263"
   )
   public int method3212() {
      this.offset += 2;
      int var1 = ((this.payload[this.offset - 1] & 255) << 8) + (this.payload[this.offset - 2] & 255);
      if(var1 > 32767) {
         var1 -= 65536;
      }

      return var1;
   }

   @ObfuscatedName("al")
   @ObfuscatedSignature(
      signature = "([II)V",
      garbageValue = "1859203817"
   )
   @Export("encryptXtea2")
   public void encryptXtea2(int[] var1) {
      int var2 = this.offset / 8;
      this.offset = 0;

      for(int var3 = 0; var3 < var2; ++var3) {
         int var4 = this.readInt();
         int var5 = this.readInt();
         int var6 = 0;
         int var7 = -1640531527;

         for(int var8 = 32; var8-- > 0; var5 += var4 + (var4 << 4 ^ var4 >>> 5) ^ var1[var6 >>> 11 & 3] + var6) {
            var4 += var5 + (var5 << 4 ^ var5 >>> 5) ^ var6 + var1[var6 & 3];
            var6 += var7;
         }

         this.offset -= 8;
         this.putInt(var4);
         this.putInt(var5);
      }

   }

   @ObfuscatedName("e")
   @ObfuscatedSignature(
      signature = "(IB)V",
      garbageValue = "0"
   )
   @Export("putLengthInt")
   public void putLengthInt(int var1) {
      this.payload[this.offset - var1 - 4] = (byte)(var1 >> 24);
      this.payload[this.offset - var1 - 3] = (byte)(var1 >> 16);
      this.payload[this.offset - var1 - 2] = (byte)(var1 >> 8);
      this.payload[this.offset - var1 - 1] = (byte)var1;
   }

   @ObfuscatedName("aw")
   @ObfuscatedSignature(
      signature = "(B)Z",
      garbageValue = "3"
   )
   @Export("checkCrc")
   public boolean checkCrc() {
      this.offset -= 4;
      int var1 = CollisionData.method3074(this.payload, 0, this.offset);
      int var2 = this.readInt();
      return var1 == var2;
   }

   @ObfuscatedName("aq")
   @ObfuscatedSignature(
      signature = "(IB)V",
      garbageValue = "15"
   )
   @Export("putLEInt")
   public void putLEInt(int var1) {
      this.payload[++this.offset - 1] = (byte)(var1 + 128);
   }

   @ObfuscatedName("ai")
   @ObfuscatedSignature(
      signature = "(II)V",
      garbageValue = "328102513"
   )
   public void method3262(int var1) {
      this.payload[++this.offset - 1] = (byte)(0 - var1);
   }

   @ObfuscatedName("ak")
   @ObfuscatedSignature(
      signature = "(IB)V",
      garbageValue = "-1"
   )
   public void method3200(int var1) {
      this.payload[++this.offset - 1] = (byte)(128 - var1);
   }

   @ObfuscatedName("bi")
   @ObfuscatedSignature(
      signature = "(B)B",
      garbageValue = "116"
   )
   public byte method3204() {
      return (byte)(this.payload[++this.offset - 1] - 128);
   }

   @ObfuscatedName("am")
   @ObfuscatedSignature(
      signature = "([IB)V",
      garbageValue = "50"
   )
   @Export("decryptXtea")
   public void decryptXtea(int[] var1) {
      int var2 = this.offset / 8;
      this.offset = 0;

      for(int var3 = 0; var3 < var2; ++var3) {
         int var4 = this.readInt();
         int var5 = this.readInt();
         int var6 = -957401312;
         int var7 = -1640531527;

         for(int var8 = 32; var8-- > 0; var4 -= var5 + (var5 << 4 ^ var5 >>> 5) ^ var6 + var1[var6 & 3]) {
            var5 -= var4 + (var4 << 4 ^ var4 >>> 5) ^ var1[var6 >>> 11 & 3] + var6;
            var6 -= var7;
         }

         this.offset -= 8;
         this.putInt(var4);
         this.putInt(var5);
      }

   }

   @ObfuscatedName("ah")
   @ObfuscatedSignature(
      signature = "([IIII)V",
      garbageValue = "-1225510345"
   )
   @Export("encryptXtea")
   public void encryptXtea(int[] var1, int var2, int var3) {
      int var4 = this.offset;
      this.offset = var2;
      int var5 = (var3 - var2) / 8;

      for(int var6 = 0; var6 < var5; ++var6) {
         int var7 = this.readInt();
         int var8 = this.readInt();
         int var9 = 0;
         int var10 = -1640531527;

         for(int var11 = 32; var11-- > 0; var8 += var7 + (var7 << 4 ^ var7 >>> 5) ^ var1[var9 >>> 11 & 3] + var9) {
            var7 += var8 + (var8 << 4 ^ var8 >>> 5) ^ var9 + var1[var9 & 3];
            var9 += var10;
         }

         this.offset -= 8;
         this.putInt(var7);
         this.putInt(var8);
      }

      this.offset = var4;
   }

   @ObfuscatedName("ag")
   @ObfuscatedSignature(
      signature = "(I)Ljava/lang/String;",
      garbageValue = "1756263677"
   )
   @Export("getNullString")
   public String getNullString() {
      if(this.payload[this.offset] == 0) {
         ++this.offset;
         return null;
      } else {
         return this.readString();
      }
   }

   @ObfuscatedName("bd")
   @ObfuscatedSignature(
      signature = "(IB)V",
      garbageValue = "-70"
   )
   public void method3173(int var1) {
      this.payload[++this.offset - 1] = (byte)(var1 >> 8);
      this.payload[++this.offset - 1] = (byte)var1;
      this.payload[++this.offset - 1] = (byte)(var1 >> 24);
      this.payload[++this.offset - 1] = (byte)(var1 >> 16);
   }

   @ObfuscatedName("t")
   @ObfuscatedSignature(
      signature = "(II)V",
      garbageValue = "-2091695950"
   )
   @Export("putShortSmart")
   public void putShortSmart(int var1) {
      if(var1 >= 0 && var1 < 128) {
         this.putByte(var1);
      } else if(var1 >= 0 && var1 < '耀') {
         this.putShort(var1 + '耀');
      } else {
         throw new IllegalArgumentException();
      }
   }

   byte runeliteReadByte() {
      ++this.offset;
      return this.payload[this.offset - 1];
   }

   short runeliteReadShort() {
      this.offset += 2;
      return (short)((this.payload[this.offset - 2] & 255) << 8 | this.payload[this.offset - 1] & 255);
   }

   int runeliteReadInt() {
      this.offset += 4;
      return (this.payload[this.offset - 4] & 255) << 24 | (this.payload[this.offset - 3] & 255) << 16 | (this.payload[this.offset - 2] & 255) << 8 | this.payload[this.offset - 1] & 255;
   }

   long runeliteReadLong() {
      this.offset += 8;
      return ((long)this.payload[this.offset - 8] & 255L) << 56 | ((long)this.payload[this.offset - 7] & 255L) << 48 | ((long)this.payload[this.offset - 6] & 255L) << 40 | ((long)this.payload[this.offset - 5] & 255L) << 32 | ((long)this.payload[this.offset - 4] & 255L) << 24 | ((long)this.payload[this.offset - 3] & 255L) << 16 | ((long)this.payload[this.offset - 2] & 255L) << 8 | (long)this.payload[this.offset - 1] & 255L;
   }

   String runeliteReadString() {
      short var1 = this.runeliteReadShort();
      if(var1 < 0) {
         throw new RuntimeException("length < 0");
      } else {
         this.offset += var1;

         try {
            return new String(this.payload, this.offset - var1, var1, "UTF-8");
         } catch (UnsupportedEncodingException var3) {
            throw new RuntimeException(var3);
         }
      }
   }

   void runeliteWriteByte(byte var1) {
      this.payload[this.offset++] = var1;
   }

   void runeliteWriteShort(short var1) {
      this.payload[this.offset++] = (byte)(var1 >> 8);
      this.payload[this.offset++] = (byte)var1;
   }

   void runeliteWriteInt(int var1) {
      this.payload[this.offset++] = (byte)(var1 >> 24);
      this.payload[this.offset++] = (byte)(var1 >> 16);
      this.payload[this.offset++] = (byte)(var1 >> 8);
      this.payload[this.offset++] = (byte)var1;
   }

   void runeliteWriteLong(long var1) {
      this.payload[this.offset++] = (byte)((int)(var1 >> 56));
      this.payload[this.offset++] = (byte)((int)(var1 >> 48));
      this.payload[this.offset++] = (byte)((int)(var1 >> 40));
      this.payload[this.offset++] = (byte)((int)(var1 >> 32));
      this.payload[this.offset++] = (byte)((int)(var1 >> 24));
      this.payload[this.offset++] = (byte)((int)(var1 >> 16));
      this.payload[this.offset++] = (byte)((int)(var1 >> 8));
      this.payload[this.offset++] = (byte)((int)var1);
   }

   void runeliteWriteString(String var1) {
      byte[] var2;
      try {
         var2 = var1.getBytes("UTF-8");
      } catch (UnsupportedEncodingException var7) {
         throw new RuntimeException(var7);
      }

      this.runeliteWriteShort((short)var2.length);
      byte[] var3 = var2;
      int var4 = var2.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         byte var6 = var3[var5];
         this.payload[this.offset++] = var6;
      }

   }
}
