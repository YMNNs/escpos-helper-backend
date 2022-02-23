package com.inspray.escposhelperbackend.util.escpos;

import java.util.ArrayList;
import java.util.List;

public class Commands {
    private static final byte NUL = 0;
    private static final byte HT = 9;
    private static final byte LF = 10;
    private static final byte FF = 12;
    private static final byte CAN = 24;
    private static final byte ESC = 27;
    private static final byte FS = 28;
    private static final byte GS = 29;
    private static final byte SP = 32;

    /**
     * 初始化打印机并清空缓冲区
     *
     * @return ESC @
     */
    public static byte[] initialize() {
        return new byte[]{ESC, '@'};
    }

    /**
     * 取消页面模式的待打印数据打印
     *
     * @return CAN
     */
    public static byte[] cancelPrintData() {
        return new byte[]{CAN};
    }

    /**
     * 设置字符右间距
     *
     * @param n n = 0 – 255
     * @return ESC SP n
     */
    public static byte[] setRightSideCharacterSpacing(int n) {
        return new byte[]{ESC, SP, (byte) n};
    }


    /**
     * 选择打印模式
     *
     * @param font         字体A或B
     * @param emphasized   加粗
     * @param doubleHeight 双倍高度
     * @param doubleWidth  双倍宽度
     * @param underline    下划线
     * @return ESC ! [font]00[emphasized][doubleHeight][doubleWidth]0[underline]
     */
    public static byte[] selectPrintMode(boolean font, boolean emphasized, boolean doubleHeight, boolean doubleWidth, boolean underline) {
        String selection = (font ? 1 : 0) + "00" + (emphasized ? 1 : 0) + (doubleHeight ? 1 : 0) + (doubleWidth ? 1 : 0) + '0' + (underline ? 1 : 0);
        return new byte[]{ESC, '!', (byte) Integer.parseInt(selection, 2)};
    }

    /**
     * （开启）下划线模式（1点厚）
     *
     * @return ESC - 1
     */
    public static byte[] underlineOn() {
        return new byte[]{ESC, '-', 1};
    }

    /**
     * （开启）下划线模式（2点厚）
     *
     * @return ESC - 2
     */
    public static byte[] underline2DotsOn() {
        return new byte[]{ESC, '-', 2};
    }

    /**
     * （关闭）下划线模式
     *
     * @return ESC - 0
     */
    public static byte[] underlineOff() {
        return new byte[]{ESC, '-', 0};
    }

    /**
     * （开启）加粗模式
     *
     * @return ESC E 1
     */
    public static byte[] emphasizeOn() {
        return new byte[]{ESC, 'E', 1};
    }

    /**
     * （关闭）加粗模式
     *
     * @return ESC E 0
     */
    public static byte[] emphasizeOff() {
        return new byte[]{ESC, 'E', 0};
    }

    /**
     * 选择字体A (12*24 dots)
     *
     * @return ESC M 0
     */
    public static byte[] fontA() {
        return new byte[]{ESC, 'M', 0};
    }

    /**
     * 选择字体B (10*24 dots)
     *
     * @return ESC M 1
     */
    public static byte[] fontB() {
        return new byte[]{ESC, 'M', 1};
    }

    /**
     * 选择国际字符集
     *
     * @param n 字符选项
     *          0 = U.S.A, 15 = China
     * @return ESC R [n]
     */
    public static byte[] selectCharacterSet(int n) {
        return new byte[]{ESC, 'R', (byte) n};
    }

    /**
     * （开启）将打印内容顺时针旋转90度
     *
     * @return ESC V 1
     */
    public static byte[] turn90ClockwiseOn() {
        return new byte[]{ESC, 'V', 1};
    }

    /**
     * （关闭）将打印内容顺时针旋转90度
     *
     * @return ESC V 0
     */
    public static byte[] turn90ClockwiseOff() {
        return new byte[]{ESC, 'V', 0};
    }

    /**
     * 选择代码页
     *
     * @param n 0 = Page 0 [PC437: USA, Standard Europe]
     * @return ESC t [n]
     */
    public static byte[] selectCharacterCode(int n) {
        return new byte[]{ESC, 't', (byte) n};
    }

    /**
     * （开启）上下翻转模式
     *
     * @return ESC { 1
     */
    public static byte[] upsideDownOn() {
        return new byte[]{ESC, '{', 1};
    }

    /**
     * （关闭）上下翻转模式
     *
     * @return ESC { 0
     */
    public static byte[] upsideDownOff() {
        return new byte[]{ESC, '{', 0};
    }

    /**
     * 设置字体大小
     *
     * @param width  宽度 1-8
     * @param height 高度 1-8
     * @return GS ! [width] [height]
     */
    public static byte[] setCharacterSize(int width, int height) {
        int _width = Integer.parseInt(String.valueOf((width - 1) * 10), 16);
        int _height = height - 1;
        return new byte[]{GS, '!', (byte) (_width + _height)};
    }

    /**
     * （开启）黑白翻转
     *
     * @return GS B 1
     */
    public static byte[] reverseOn() {
        return new byte[]{GS, 'B', 1};
    }

    /**
     * （关闭）黑白翻转
     *
     * @return GS B 0
     */
    public static byte[] reverseOff() {
        return new byte[]{GS, 'B', 0};
    }

    /**
     * 设置为默认行距
     *
     * @return ESC 2
     */
    public static byte[] defaultLineSpacing() {
        return new byte[]{ESC, '2'};
    }

    /**
     * 设置指定行距
     *
     * @param n n = 0 – 255
     * @return ESC 3 [n]
     */
    public static byte[] defaultLineSpacing(int n) {
        return new byte[]{ESC, '3', (byte) n};
    }

    /**
     * 打印并换行
     * <p>
     * 打印缓冲区中的数据并换一行。
     *
     * @return LF
     */
    public static byte[] lineFeed() {
        return new byte[]{LF};
    }

    /**
     * 打印并从页模式返回标准模式
     *
     * @return FF
     */
    public static byte[] StandardMode() {
        return new byte[]{FF};
    }

    /**
     * 打印并进纸
     * <p>
     * 打印缓冲区中的数据并进纸n个垂直运动单位
     *
     * @param n n = 0 – 255
     * @return ESC J n
     */
    public static byte[] printAndFeedPaper(int n) {
        return new byte[]{ESC, 'J', (byte) n};
    }

    /**
     * 打印并退纸
     * <p>
     * 打印缓冲区中的数据并退纸n个垂直运动单位
     *
     * @param n n = 0 – 255
     * @return ESC K n
     */
    public static byte[] reverseFeed(int n) {
        return new byte[]{ESC, 'K', (byte) n};
    }

    /**
     * 打印并进纸n行
     *
     * @param n n = 0 – 255
     * @return ESC d n
     */
    public static byte[] feedLines(int n) {
        return new byte[]{ESC, 'd', (byte) n};
    }

    /**
     * 打印并退纸n行
     *
     * @param n n = 0 – 255
     * @return ESC e n
     */
    public static byte[] reverseLines(int n) {
        return new byte[]{ESC, 'e', (byte) n};
    }

    /**
     * 水平制表符
     *
     * @return HT
     */
    public static byte[] horizontalTab() {
        return new byte[]{HT};
    }

    /**
     * 设置绝对打印位置
     *
     * @param x 从打印位置左侧移动的距离
     * @return ESC $ [nL] [nH]
     */
    public static byte[] setAbsolutePrintPosition(int x) {
        return new byte[]{ESC, '$', (byte) (x % 256), (byte) (x / 256)};
    }

    /**
     * 设置水平制表符位置
     *
     * @param ns 指定位置到打印区域左边缘位数（最多32个）
     * @return ESC D [n1] [n2] ... NUL
     */
    public static byte[] setHorizontalTabPosition(int[] ns) {
        byte[] res = new byte[ns.length + 3];
        res[0] = ESC;
        res[1] = 'D';
        for (int i = 0; i < ns.length; i++) {
            res[i + 2] = (byte) ns[i];
        }
        return res;
    }

    /**
     * 取消水平制表符位置
     *
     * @return ESC D NUL
     */
    public static byte[] cancelHorizontalTabPosition() {
        return new byte[]{ESC, 'D', NUL};
    }

    /**
     * 设置页面模式的打印方向（只在页面模式中有效）
     *
     * @param direction 0=从左到右（左上角开始） 1=从下到上（左下角开始） 2=从右到左（右下角开始） 3=从上到下（右上角开始）
     * @return ESC T [direction]
     */
    public static byte[] setPageModeDirection(int direction) {
        return new byte[]{ESC, 'T', (byte) direction};
    }

    /**
     * 设置页面模式的打印区域
     *
     * @param x      水平方向起始点
     * @param y      垂直方向起始点
     * @param width  宽度
     * @param height 高度
     * @return ESC W [xL] [xH] [yL] [yH] [dxL] [dxH] [dyL] [dyH]
     */
    public static byte[] setPageModePrintArea(int x, int y, int width, int height) {
        return new byte[]{ESC, 'W', (byte) (x % 256), (byte) (x / 256), (byte) (y % 256), (byte) (y / 256), (byte) (width % 256), (byte) (width / 256), (byte) (height % 256), (byte) (height / 256)};
    }

    /**
     * 设置相对打印位置
     *
     * @param x 从打印位置左侧移动的距离
     * @return ESC \ [nL] [nH]
     */
    public static byte[] setRelativePrintPosition(int x) {
        return new byte[]{ESC, '\\', (byte) (x % 256), (byte) (x / 256)};
    }

    /**
     * 一行内左对齐
     *
     * @return ESC a 0
     */
    public static byte[] alignLeft() {
        return new byte[]{ESC, 'a', 0};
    }

    /**
     * 一行内居中对齐
     *
     * @return ESC a 1
     */
    public static byte[] alignCenter() {
        return new byte[]{ESC, 'a', 1};
    }

    /**
     * 一行内右对齐
     *
     * @return ESC a 2
     */
    public static byte[] alignRight() {
        return new byte[]{ESC, 'a', 2};
    }

    /**
     * 设置页面模式垂直方向绝对打印位置
     *
     * @param y 位置
     * @return GS $ [nL] [nH]
     */
    public static byte[] setPageModeAbsoluteVerticalPrintPosition(int y) {
        return new byte[]{GS, '$', (byte) (y % 256), (byte) (y / 256)};
    }

    /**
     * 设置左页边距
     *
     * @param x 左页边距
     * @return GS L [nL] [nH]
     */
    public static byte[] setLeftMargin(int x) {
        return new byte[]{GS, 'L', (byte) (x % 256), (byte) (x / 256)};
    }

    /**
     * 取消缓冲区的内容并将打印位置移至行首
     *
     * @return GS T 0
     */
    public static byte[] eraseAndSetPrintPositionToBeginning() {
        return new byte[]{GS, 'T', 0};
    }

    /**
     * 打印缓冲区的内容并将打印位置移至行首
     *
     * @return GS T 1
     */
    public static byte[] PrintAndSetPrintPositionToBeginning() {
        return new byte[]{GS, 'T', 1};
    }

    /**
     * 设置打印区域宽度
     *
     * @param x 576=48列 546=42列
     * @return GS W [nL] [nH]
     */
    public static byte[] setPrintAreaWidth(int x) {
        return new byte[]{GS, 'W', (byte) (x % 256), (byte) (x / 256)};
    }

    /**
     * 设置页面模式垂直方向相对打印位置
     *
     * @param y 位置
     * @return GS \ [nL] [nH]
     */
    public static byte[] setPageModeRelativeVerticalPrintPosition(int y) {
        return new byte[]{GS, '\\', (byte) (y % 256), (byte) (y / 256)};
    }

    /**
     * 进纸并切纸
     *
     * @param n 行数
     * @return GS V [n]
     */
    public static byte[] cutAndFeed(int n) {
        return new byte[]{GS, 'V', 66, (byte) n};
    }

    /**
     * 直接切纸（可能截断打印内容）
     *
     * @return GS V 0
     */
    public static byte[] cut() {
        return new byte[]{GS, 'V', 0};
    }

    /**
     * 选择条码字符位置
     *
     * @param n 0=不显示 1=上面 2=下面 3=上面和下面
     * @return GS H [n]
     */
    public static byte[] setBarcodeHriPosition(int n) {
        return new byte[]{GS, 'H', (byte) n};
    }

    /**
     * 选择条码字符字体
     *
     * @param n 0=Font A 1=Font B
     * @return GS f [n]
     */
    public static byte[] setBarcodeHriFont(int n) {
        return new byte[]{GS, 'f', (byte) n};
    }

    /**
     * 设置条码高度
     *
     * @param n n = 1-255
     * @return GS h [n]
     */
    public static byte[] setBarcodeHeight(int n) {
        return new byte[]{GS, 'h', (byte) n};
    }

    /**
     * 设置条码宽度
     *
     * @param n n = 2-6
     * @return GS w [n]
     */
    public static byte[] setBarcodeWidth(int n) {
        return new byte[]{GS, 'w', (byte) n};
    }

    /**
     * 打印条形码
     *
     * @param type 条码类型
     * @param data 条码数据
     *             <p>
     *             编号  类型                        长度             数据                          限制
     *             0    UPC-A                       11,12           0-9
     *             1    UPC-E                       6-8,11,12       0-9
     *             2    JAN13/EAN13                 12,13           0-9
     *             3    JAN8/EAN8                   7,8             0-9
     *             4    CODE39                      >=1             0-9,A-Z,SP,&,%,*,+,-,.,/
     *             5    ITF                         >=2(even)       0-9
     *             6    CODABAR                     >=2             0-9,A-D,a-d,$,+,-,.,/,:
     *             65   UPC-A                       11,12           0-9
     *             66   UPC-B                       6-8,11,12       0-9                         d1=0 when length= 7,8,11,12
     *             67   JAN13/EAN13                 12,13           0-9
     *             68   JAN8/EAN8                   7,8             0-9
     *             69   CODE39                      1-255           0-9,A-Z,SP,&,%,*,+,-,.,/
     *             70   ITF                         2-254(even)     0-9
     *             71   CODABAR                     2-255           0-9,A-D,a-d,$,+,-,.,/,:     d1=A,B,C,D dn=A,B,C,D d1=a,b,c,d dn=a,b,c,d
     *             72   CODE93                      1-255           00h – 7Fh(ASCII)
     *             73   CODE128                     1-255           00h – 7Fh(ASCII)            d1={ d2=A,B,C
     *             74   GS1-128                     2-255           00h – 7Fh(ASCII)
     *             75   GS1 DataBar Omnidirectional 13              0-9
     *             76   GS1 DataBar Truncated       13              0-9
     *             77   GS1 DataBar Limited         13              0-9
     *             78   GS1 DataBar Expanded        2-255           0–9,A–D,a–d,SP,!,",%,$,',(,),*,+,,,-,.,/,:,;,<,=,>,?,_,{
     *             79   Code128 auto                1-255           FF
     * @return GS k [type] [data]
     */
    public static byte[] barcode(int type, String data) {
        byte[] res = new byte[data.length() + 4];
        res[0] = GS;
        res[1] = 'k';
        res[2] = (byte) type;
        if (type < 7) {
            System.arraycopy(data.getBytes(), 0, res, 3, data.length());
        } else {
            res[3] = (byte) data.length();
            System.arraycopy(data.getBytes(), 0, res, 4, data.length());
        }
        return res;
    }

    /**
     * 进入页面模式
     *
     * @return ESC L
     */
    public static byte[] pageMode() {
        return new byte[]{ESC, 'L'};
    }

    /**
     * 进入行式模式
     *
     * @return ESC S
     */
    public static byte[] standardMode() {
        return new byte[]{ESC, 'S'};
    }

    /**
     * 打印页面模式缓冲区的数据
     *
     * @return ESC FF
     */
    public static byte[] printInPageMode() {
        return new byte[]{ESC, FF};
    }

    /**
     * 设置水平和垂直移动单位距离
     *
     * @param x 水平单位距离 0-255
     * @param y 垂直单位距离 0-255
     * @return GS P [x] [y]
     */
    public static byte[] setMotionUnit(int x, int y) {
        return new byte[]{GS, 'P', (byte) x, (byte) y};
    }

    /**
     * 打印PDF417二维码
     *
     * @param data                 数据
     * @param numberOfColumns      列数 0=自动
     * @param numberOfRows         行数 0=自动
     * @param width                宽度 1-8 默认 3
     * @param height               高度 2-8 默认 3
     * @param errorCorrectionLevel 纠错等级 0-8
     * @param option               0=标准 1=截短
     * @return GS ( k [多个命令]
     */
    public static List<byte[]> pdf417(String data, int numberOfColumns, int numberOfRows, int width, int height, int errorCorrectionLevel, int option) {
        List<byte[]> resList = new ArrayList<>();

        resList.add(new byte[]{GS, '(', 'k', 3, 0, 48, 65, (byte) numberOfColumns});
        resList.add(new byte[]{GS, '(', 'k', 3, 0, 48, 66, (byte) numberOfRows});
        resList.add(new byte[]{GS, '(', 'k', 3, 0, 48, 67, (byte) width});
        resList.add(new byte[]{GS, '(', 'k', 3, 0, 48, 68, (byte) height});
        resList.add(new byte[]{GS, '(', 'k', 4, 0, 48, 69, 48, String.valueOf(errorCorrectionLevel).getBytes()[0]});
        resList.add(new byte[]{GS, '(', 'k', 3, 0, 48, 70, (byte) option});
        resList.add(twoDimensionalCodeData(data, 48));
        resList.add(new byte[]{GS, '(', 'k', 3, 0, 48, 81, 48});

        return resList;
    }

    /**
     * 打印QR二维码
     *
     * @param data                 数据
     * @param type                 类型 1,2
     * @param size                 尺寸 1-16
     * @param errorCorrectionLevel 纠错等级 0-3
     * @return GS ( k [多个命令]
     */
    public static List<byte[]> qrCode(String data, int type, int size, int errorCorrectionLevel) {
        List<byte[]> resList = new ArrayList<>();
        resList.add(new byte[]{GS, '(', 'k', 4, 0, 49, 65, String.valueOf(type).getBytes()[0], 0});
        resList.add(new byte[]{GS, '(', 'k', 3, 0, 49, 67, (byte) size});
        resList.add(new byte[]{GS, '(', 'k', 3, 0, 49, 69, String.valueOf(errorCorrectionLevel).getBytes()[0]});
        resList.add(twoDimensionalCodeData(data, 49));
        resList.add(new byte[]{GS, '(', 'k', 3, 0, 49, 81, 48});
        return resList;
    }

    /**
     * 打印MaxiCode二维码
     *
     * @param data 数据
     * @param type 类型 2-6
     * @return GS ( k [多个命令]
     */
    public static List<byte[]> maxiCode(String data, int type) {
        List<byte[]> resList = new ArrayList<>();
        resList.add(new byte[]{GS, '(', 'k', 3, 0, 50, 65, String.valueOf(type).getBytes()[0]});
        resList.add(twoDimensionalCodeData(data, 50));
        resList.add(new byte[]{GS, '(', 'k', 3, 0, 50, 81, 48});
        return resList;
    }

    /**
     * 构造二维码数据
     *
     * @param data 数据
     * @param cn   二维码类型
     * @return GS ( k [pL] [pH] [cn] [80] [48] [d1...dk]
     */
    private static byte[] twoDimensionalCodeData(String data, int cn) {
        byte[] _data = new byte[data.length() + 8];
        _data[0] = GS;
        _data[1] = '(';
        _data[2] = 'k';
        _data[3] = (byte) ((data.length() + 3) % 256);
        _data[4] = (byte) ((data.length() + 3) / 256);
        _data[5] = (byte) cn;
        _data[6] = 80;
        _data[7] = 48;
        System.arraycopy(data.getBytes(), 0, _data, 8, data.length());
        return _data;
    }

    /**
     * 打印位图
     *
     * @param mode 功能 0=8点单密度浮点 1=8点双密度浮点 32=24点单密度浮点 33=24点双密度浮点
     * @param data 数据 1=打印 0=不打印
     * @param size 位图在水平方向的点数
     * @return ESC * [m] [nL] [nH] [d1...dk]
     */
    public static byte[] selectBitImageMode(int mode, int[] data, int size) {
        byte[] _data = new byte[data.length + 5];
        _data[0] = ESC;
        _data[1] = '*';
        _data[2] = (byte) mode;
        _data[3] = (byte) (size % 256);
        _data[4] = (byte) (size / 256);
        for (int i = 0; i < data.length; i++) {
            _data[i + 5] = (byte) data[i];
        }
        return _data;
    }


}
