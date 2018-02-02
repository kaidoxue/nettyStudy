package study.netty.prog1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import study.netty.utils.StringReverseBySingleDot;

public class HWClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String retStr = msg.toString();
        System.out.println("server say : "+retStr);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client is active");

//        ByteBuf message;
//        byte[] req = ("Unless required by applicable law or agreed to in writing, software\t" +
//                "  distribu$$_ted under the License is distributed on an \"AS IS\" BASIS,\t$$_"
//        ).getBytes();
//        message = Unpooled.buffer(req.length);
//        message.writeBytes(req);
//        ctx.writeAndFlush(message);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client is close");
    }
}
