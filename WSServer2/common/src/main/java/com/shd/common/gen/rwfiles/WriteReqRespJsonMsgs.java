package com.shd.common.gen.rwfiles;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shd.common.msgelms.Header;
import com.shd.common.msgelms.ResponseMsgTypes;

//import com.shd.server.Ref2Singleton;

public class WriteReqRespJsonMsgs {

	private  String   dirPrefix= null ;				//   "ReqResp_" ;
	private  String   inpHeader =null ; 			//	"C:/WebSockt_WrkDirec/Server/ReqRespMsgs" ;
	private  Path   useReqPath = null ;
	private  Path   useRespPath = null ;
    private Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
    private   AtomicInteger atomicProgMsgNo = new AtomicInteger(1);
;
	//	public static void main(String[] args) throws IOException {
	//		WriteReqRespJsonMsgs ws = new WriteReqRespJsonMsgs() ;
	//		ws.createNewDirs() ;
	//	}
private <ReqOrRespObj> void writePath (Header head, ReqOrRespObj reqOrRespObj ,Path filePath ) {
	
	try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
		String jsonHeadOutput = gsonPretty.toJson(head) ;
		String jsonReqOutput = gsonPretty.toJson(reqOrRespObj) ;
		writer.write(jsonHeadOutput) ; 
		writer.newLine();
		writer.write(jsonReqOutput);
		writer.close();
	} catch (IOException x) {
		System.err.format("IOException: %s%n", x);
	}
}

public <ReqObj>  void writeReqMsg(Header headObj,ReqObj  reqObji )  
{
	Path filePath = null ;
	filePath = Paths.get(useReqPath.toString()+"/"+headObj.getReqResId()+"_Req.txt" ) ;		
	writePath (headObj , reqObji, filePath ) ;
}
public <MObj> void writeRespMsg(Header head, MObj mObj,ResponseMsgTypes typeResp) 
	{
		
		Path filePath = null ;
		if (typeResp.isEndOfResp(typeResp))
			{
			 filePath = Paths.get(useRespPath.toString()+"/"+head.getReqResId()+"_Resp.txt" ) ;		
		    }
		else
		{
			int pmsgNo = atomicProgMsgNo.getAndIncrement() ;
			filePath = Paths.get(useRespPath.toString()+"/"+head.getReqResId()+"_Msg_" + pmsgNo+".txt" ) ;
		}
		writePath (head, mObj ,filePath ) ;
		
	}
	public void cleanBeforeStart() throws IOException  //C:\WebSockt_WrkDirec\Server\ReqRespMsgs\*
	{
		String wDirec = getInpHeader() ;
		Path pathwDirec = Paths.get(wDirec);
		System.out.println ("WriteReqRespJsonMsgs.cleanBeforeStart  ..... ") ;
		Files.walk(pathwDirec)
		.sorted(Comparator.reverseOrder())
		.map(Path::toFile)
		.forEach(f -> {
			if ( f.toPath().compareTo(pathwDirec) != 0 )						
			{
				System.out.println ("WriteReqRespJsonMsgs.cleanBeforeStarted Delete:[" + f.getAbsolutePath()) ;
				f.delete() ;
				//File::delete ;
			}});
		System.out.println ("WriteReqRespJsonMsgs.cleanBeforeStart Completed ..... ") ;
	}

	public void createNewDirs() throws IOException
	{
		int iRetMax =getNextSeqForWrite(getInpHeader()) ;
		String newDirec = getInpHeader()+"/"+getDirPrefix()+String.valueOf(iRetMax+1) ;
		Path pathNew = Paths.get(newDirec);
		Files.createDirectories(pathNew);
		System.out.println("WriteReqRespJsonMsgs Enabled and Create the newDirectory =[" + newDirec + "]" ) ;
		Path pathNewReq = Paths.get(newDirec+"/Req");
		Files.createDirectories(pathNewReq);
		System.out.println("WriteReqRespJsonMsgs Create the ReqNewDirectory =[" + pathNewReq + "]" ) ;
		setUseReqPath(pathNewReq);
		Path pathNewResp = Paths.get(newDirec+"/Resp");
		Files.createDirectories(pathNewResp);
		System.out.println("WriteReqRespJsonMsgs Create the RespNewDirectory =[" + pathNewResp + "]" ) ;
		setUseRespPath(pathNewResp);
	}
	private int getNextSeqForWrite(String fqDirName) throws IOException
	{
		//Pattern ReqResp_nn we need to the Max nn for ReqResp Directories

		Stream<Path> walk = Files.walk(Paths.get(fqDirName)) ;
		int maxToRet =  walk.filter(Files::isDirectory)
				//.peek(xxxx -> System.out.println("xxxx.toString()" + xxxx.toString()))
				.map(x -> x.getFileName().toString() )
				.filter(f -> f.contains(dirPrefix))
				//.peek(fn -> System.out.println("fnOnly[" + fn +']'))
				//.mapToInt(xx -> Integer.valueOf(xx.substring(xx.indexOf("_") +1)))
				.mapToInt(xx -> Integer.valueOf(xx.replace(dirPrefix, "")))
				// .peek(iii -> System.out.println("iii" + iii ))
				.max().orElse(0) ;
		return maxToRet ;
	}
	public void setDirPrefix(String filePrefix) {
		this.dirPrefix = filePrefix;
	}
	public void setInpHeader(String inpHeader) {
		this.inpHeader = inpHeader;
	}
	public String getDirPrefix () 
	{
		return dirPrefix;
	}

	public String getInpHeader() {
		return inpHeader;
	}

	public Path getUseReqPath() {
		return useReqPath;
	}

	public void setUseReqPath(Path useReqPath) {
		this.useReqPath = useReqPath;
	}

	public Path getUseRespPath() {
		return useRespPath;
	}

	public void setUseRespPath(Path useRespPath) {
		this.useRespPath = useRespPath;
	}




}
