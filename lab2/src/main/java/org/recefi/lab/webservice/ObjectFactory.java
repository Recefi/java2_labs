
package org.recefi.lab.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.recefi.lab.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CheckMove_QNAME = new QName("http://server.lab.recefi.org/", "checkMove");
    private final static QName _CheckMoveResponse_QNAME = new QName("http://server.lab.recefi.org/", "checkMoveResponse");
    private final static QName _CheckStart_QNAME = new QName("http://server.lab.recefi.org/", "checkStart");
    private final static QName _CheckStartResponse_QNAME = new QName("http://server.lab.recefi.org/", "checkStartResponse");
    private final static QName _CheckWin_QNAME = new QName("http://server.lab.recefi.org/", "checkWin");
    private final static QName _CheckWinResponse_QNAME = new QName("http://server.lab.recefi.org/", "checkWinResponse");
    private final static QName _Connect_QNAME = new QName("http://server.lab.recefi.org/", "connect");
    private final static QName _ConnectResponse_QNAME = new QName("http://server.lab.recefi.org/", "connectResponse");
    private final static QName _GetBoard_QNAME = new QName("http://server.lab.recefi.org/", "getBoard");
    private final static QName _GetBoardResponse_QNAME = new QName("http://server.lab.recefi.org/", "getBoardResponse");
    private final static QName _MakeMove1_QNAME = new QName("http://server.lab.recefi.org/", "makeMove1");
    private final static QName _MakeMove1Response_QNAME = new QName("http://server.lab.recefi.org/", "makeMove1Response");
    private final static QName _MakeMove2_QNAME = new QName("http://server.lab.recefi.org/", "makeMove2");
    private final static QName _MakeMove2Response_QNAME = new QName("http://server.lab.recefi.org/", "makeMove2Response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.recefi.lab.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckMove }
     * 
     */
    public CheckMove createCheckMove() {
        return new CheckMove();
    }

    /**
     * Create an instance of {@link CheckMoveResponse }
     * 
     */
    public CheckMoveResponse createCheckMoveResponse() {
        return new CheckMoveResponse();
    }

    /**
     * Create an instance of {@link CheckStart }
     * 
     */
    public CheckStart createCheckStart() {
        return new CheckStart();
    }

    /**
     * Create an instance of {@link CheckStartResponse }
     * 
     */
    public CheckStartResponse createCheckStartResponse() {
        return new CheckStartResponse();
    }

    /**
     * Create an instance of {@link CheckWin }
     * 
     */
    public CheckWin createCheckWin() {
        return new CheckWin();
    }

    /**
     * Create an instance of {@link CheckWinResponse }
     * 
     */
    public CheckWinResponse createCheckWinResponse() {
        return new CheckWinResponse();
    }

    /**
     * Create an instance of {@link Connect }
     * 
     */
    public Connect createConnect() {
        return new Connect();
    }

    /**
     * Create an instance of {@link ConnectResponse }
     * 
     */
    public ConnectResponse createConnectResponse() {
        return new ConnectResponse();
    }

    /**
     * Create an instance of {@link GetBoard }
     * 
     */
    public GetBoard createGetBoard() {
        return new GetBoard();
    }

    /**
     * Create an instance of {@link GetBoardResponse }
     * 
     */
    public GetBoardResponse createGetBoardResponse() {
        return new GetBoardResponse();
    }

    /**
     * Create an instance of {@link MakeMove1 }
     * 
     */
    public MakeMove1 createMakeMove1() {
        return new MakeMove1();
    }

    /**
     * Create an instance of {@link MakeMove1Response }
     * 
     */
    public MakeMove1Response createMakeMove1Response() {
        return new MakeMove1Response();
    }

    /**
     * Create an instance of {@link MakeMove2 }
     * 
     */
    public MakeMove2 createMakeMove2() {
        return new MakeMove2();
    }

    /**
     * Create an instance of {@link MakeMove2Response }
     * 
     */
    public MakeMove2Response createMakeMove2Response() {
        return new MakeMove2Response();
    }

    /**
     * Create an instance of {@link Cell }
     * 
     */
    public Cell createCell() {
        return new Cell();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckMove }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckMove }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "checkMove")
    public JAXBElement<CheckMove> createCheckMove(CheckMove value) {
        return new JAXBElement<CheckMove>(_CheckMove_QNAME, CheckMove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckMoveResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckMoveResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "checkMoveResponse")
    public JAXBElement<CheckMoveResponse> createCheckMoveResponse(CheckMoveResponse value) {
        return new JAXBElement<CheckMoveResponse>(_CheckMoveResponse_QNAME, CheckMoveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckStart }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckStart }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "checkStart")
    public JAXBElement<CheckStart> createCheckStart(CheckStart value) {
        return new JAXBElement<CheckStart>(_CheckStart_QNAME, CheckStart.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckStartResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckStartResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "checkStartResponse")
    public JAXBElement<CheckStartResponse> createCheckStartResponse(CheckStartResponse value) {
        return new JAXBElement<CheckStartResponse>(_CheckStartResponse_QNAME, CheckStartResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckWin }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckWin }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "checkWin")
    public JAXBElement<CheckWin> createCheckWin(CheckWin value) {
        return new JAXBElement<CheckWin>(_CheckWin_QNAME, CheckWin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckWinResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckWinResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "checkWinResponse")
    public JAXBElement<CheckWinResponse> createCheckWinResponse(CheckWinResponse value) {
        return new JAXBElement<CheckWinResponse>(_CheckWinResponse_QNAME, CheckWinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Connect }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Connect }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "connect")
    public JAXBElement<Connect> createConnect(Connect value) {
        return new JAXBElement<Connect>(_Connect_QNAME, Connect.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConnectResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "connectResponse")
    public JAXBElement<ConnectResponse> createConnectResponse(ConnectResponse value) {
        return new JAXBElement<ConnectResponse>(_ConnectResponse_QNAME, ConnectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBoard }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetBoard }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "getBoard")
    public JAXBElement<GetBoard> createGetBoard(GetBoard value) {
        return new JAXBElement<GetBoard>(_GetBoard_QNAME, GetBoard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBoardResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetBoardResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "getBoardResponse")
    public JAXBElement<GetBoardResponse> createGetBoardResponse(GetBoardResponse value) {
        return new JAXBElement<GetBoardResponse>(_GetBoardResponse_QNAME, GetBoardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeMove1 }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakeMove1 }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "makeMove1")
    public JAXBElement<MakeMove1> createMakeMove1(MakeMove1 value) {
        return new JAXBElement<MakeMove1>(_MakeMove1_QNAME, MakeMove1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeMove1Response }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakeMove1Response }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "makeMove1Response")
    public JAXBElement<MakeMove1Response> createMakeMove1Response(MakeMove1Response value) {
        return new JAXBElement<MakeMove1Response>(_MakeMove1Response_QNAME, MakeMove1Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeMove2 }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakeMove2 }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "makeMove2")
    public JAXBElement<MakeMove2> createMakeMove2(MakeMove2 value) {
        return new JAXBElement<MakeMove2>(_MakeMove2_QNAME, MakeMove2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeMove2Response }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakeMove2Response }{@code >}
     */
    @XmlElementDecl(namespace = "http://server.lab.recefi.org/", name = "makeMove2Response")
    public JAXBElement<MakeMove2Response> createMakeMove2Response(MakeMove2Response value) {
        return new JAXBElement<MakeMove2Response>(_MakeMove2Response_QNAME, MakeMove2Response.class, null, value);
    }

}
