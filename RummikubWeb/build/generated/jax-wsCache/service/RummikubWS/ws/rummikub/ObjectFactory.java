
package ws.rummikub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.rummikub package. 
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

    private final static QName _FinishTurn_QNAME = new QName("http://rummikub.ws/", "finishTurn");
    private final static QName _GameDoesNotExists_QNAME = new QName("http://rummikub.ws/", "GameDoesNotExists");
    private final static QName _GetEvents_QNAME = new QName("http://rummikub.ws/", "getEvents");
    private final static QName _JoinGame_QNAME = new QName("http://rummikub.ws/", "joinGame");
    private final static QName _MoveTile_QNAME = new QName("http://rummikub.ws/", "moveTile");
    private final static QName _CreateGame_QNAME = new QName("http://rummikub.ws/", "createGame");
    private final static QName _GetWaitingGames_QNAME = new QName("http://rummikub.ws/", "getWaitingGames");
    private final static QName _GetEventsResponse_QNAME = new QName("http://rummikub.ws/", "getEventsResponse");
    private final static QName _CreateGameResponse_QNAME = new QName("http://rummikub.ws/", "createGameResponse");
    private final static QName _GetPlayersDetails_QNAME = new QName("http://rummikub.ws/", "getPlayersDetails");
    private final static QName _GetGameDetails_QNAME = new QName("http://rummikub.ws/", "getGameDetails");
    private final static QName _TakeBackTile_QNAME = new QName("http://rummikub.ws/", "takeBackTile");
    private final static QName _FinishTurnResponse_QNAME = new QName("http://rummikub.ws/", "finishTurnResponse");
    private final static QName _GetWaitingGamesResponse_QNAME = new QName("http://rummikub.ws/", "getWaitingGamesResponse");
    private final static QName _ResignResponse_QNAME = new QName("http://rummikub.ws/", "resignResponse");
    private final static QName _AddTileResponse_QNAME = new QName("http://rummikub.ws/", "addTileResponse");
    private final static QName _MoveTileResponse_QNAME = new QName("http://rummikub.ws/", "moveTileResponse");
    private final static QName _JoinGameResponse_QNAME = new QName("http://rummikub.ws/", "joinGameResponse");
    private final static QName _InvalidParameters_QNAME = new QName("http://rummikub.ws/", "InvalidParameters");
    private final static QName _GetPlayerDetails_QNAME = new QName("http://rummikub.ws/", "getPlayerDetails");
    private final static QName _CreateSequence_QNAME = new QName("http://rummikub.ws/", "createSequence");
    private final static QName _TakeBackTileResponse_QNAME = new QName("http://rummikub.ws/", "takeBackTileResponse");
    private final static QName _DuplicateGameName_QNAME = new QName("http://rummikub.ws/", "DuplicateGameName");
    private final static QName _CreateGameFromXMLResponse_QNAME = new QName("http://rummikub.ws/", "createGameFromXMLResponse");
    private final static QName _Resign_QNAME = new QName("http://rummikub.ws/", "resign");
    private final static QName _GetPlayersDetailsResponse_QNAME = new QName("http://rummikub.ws/", "getPlayersDetailsResponse");
    private final static QName _CreateGameFromXML_QNAME = new QName("http://rummikub.ws/", "createGameFromXML");
    private final static QName _InvalidXML_QNAME = new QName("http://rummikub.ws/", "InvalidXML");
    private final static QName _AddTile_QNAME = new QName("http://rummikub.ws/", "addTile");
    private final static QName _GetPlayerDetailsResponse_QNAME = new QName("http://rummikub.ws/", "getPlayerDetailsResponse");
    private final static QName _GetGameDetailsResponse_QNAME = new QName("http://rummikub.ws/", "getGameDetailsResponse");
    private final static QName _CreateSequenceResponse_QNAME = new QName("http://rummikub.ws/", "createSequenceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.rummikub
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FinishTurnResponse }
     * 
     */
    public FinishTurnResponse createFinishTurnResponse() {
        return new FinishTurnResponse();
    }

    /**
     * Create an instance of {@link GetWaitingGamesResponse }
     * 
     */
    public GetWaitingGamesResponse createGetWaitingGamesResponse() {
        return new GetWaitingGamesResponse();
    }

    /**
     * Create an instance of {@link TakeBackTile }
     * 
     */
    public TakeBackTile createTakeBackTile() {
        return new TakeBackTile();
    }

    /**
     * Create an instance of {@link GetGameDetails }
     * 
     */
    public GetGameDetails createGetGameDetails() {
        return new GetGameDetails();
    }

    /**
     * Create an instance of {@link ResignResponse }
     * 
     */
    public ResignResponse createResignResponse() {
        return new ResignResponse();
    }

    /**
     * Create an instance of {@link AddTileResponse }
     * 
     */
    public AddTileResponse createAddTileResponse() {
        return new AddTileResponse();
    }

    /**
     * Create an instance of {@link InvalidParameters }
     * 
     */
    public InvalidParameters createInvalidParameters() {
        return new InvalidParameters();
    }

    /**
     * Create an instance of {@link JoinGameResponse }
     * 
     */
    public JoinGameResponse createJoinGameResponse() {
        return new JoinGameResponse();
    }

    /**
     * Create an instance of {@link MoveTileResponse }
     * 
     */
    public MoveTileResponse createMoveTileResponse() {
        return new MoveTileResponse();
    }

    /**
     * Create an instance of {@link GetEvents }
     * 
     */
    public GetEvents createGetEvents() {
        return new GetEvents();
    }

    /**
     * Create an instance of {@link JoinGame }
     * 
     */
    public JoinGame createJoinGame() {
        return new JoinGame();
    }

    /**
     * Create an instance of {@link GameDoesNotExists }
     * 
     */
    public GameDoesNotExists createGameDoesNotExists() {
        return new GameDoesNotExists();
    }

    /**
     * Create an instance of {@link FinishTurn }
     * 
     */
    public FinishTurn createFinishTurn() {
        return new FinishTurn();
    }

    /**
     * Create an instance of {@link CreateGame }
     * 
     */
    public CreateGame createCreateGame() {
        return new CreateGame();
    }

    /**
     * Create an instance of {@link GetWaitingGames }
     * 
     */
    public GetWaitingGames createGetWaitingGames() {
        return new GetWaitingGames();
    }

    /**
     * Create an instance of {@link MoveTile }
     * 
     */
    public MoveTile createMoveTile() {
        return new MoveTile();
    }

    /**
     * Create an instance of {@link CreateGameResponse }
     * 
     */
    public CreateGameResponse createCreateGameResponse() {
        return new CreateGameResponse();
    }

    /**
     * Create an instance of {@link GetPlayersDetails }
     * 
     */
    public GetPlayersDetails createGetPlayersDetails() {
        return new GetPlayersDetails();
    }

    /**
     * Create an instance of {@link GetEventsResponse }
     * 
     */
    public GetEventsResponse createGetEventsResponse() {
        return new GetEventsResponse();
    }

    /**
     * Create an instance of {@link InvalidXML }
     * 
     */
    public InvalidXML createInvalidXML() {
        return new InvalidXML();
    }

    /**
     * Create an instance of {@link CreateGameFromXML }
     * 
     */
    public CreateGameFromXML createCreateGameFromXML() {
        return new CreateGameFromXML();
    }

    /**
     * Create an instance of {@link AddTile }
     * 
     */
    public AddTile createAddTile() {
        return new AddTile();
    }

    /**
     * Create an instance of {@link GetPlayerDetailsResponse }
     * 
     */
    public GetPlayerDetailsResponse createGetPlayerDetailsResponse() {
        return new GetPlayerDetailsResponse();
    }

    /**
     * Create an instance of {@link GetGameDetailsResponse }
     * 
     */
    public GetGameDetailsResponse createGetGameDetailsResponse() {
        return new GetGameDetailsResponse();
    }

    /**
     * Create an instance of {@link CreateSequenceResponse }
     * 
     */
    public CreateSequenceResponse createCreateSequenceResponse() {
        return new CreateSequenceResponse();
    }

    /**
     * Create an instance of {@link CreateSequence }
     * 
     */
    public CreateSequence createCreateSequence() {
        return new CreateSequence();
    }

    /**
     * Create an instance of {@link TakeBackTileResponse }
     * 
     */
    public TakeBackTileResponse createTakeBackTileResponse() {
        return new TakeBackTileResponse();
    }

    /**
     * Create an instance of {@link GetPlayerDetails }
     * 
     */
    public GetPlayerDetails createGetPlayerDetails() {
        return new GetPlayerDetails();
    }

    /**
     * Create an instance of {@link DuplicateGameName }
     * 
     */
    public DuplicateGameName createDuplicateGameName() {
        return new DuplicateGameName();
    }

    /**
     * Create an instance of {@link CreateGameFromXMLResponse }
     * 
     */
    public CreateGameFromXMLResponse createCreateGameFromXMLResponse() {
        return new CreateGameFromXMLResponse();
    }

    /**
     * Create an instance of {@link GetPlayersDetailsResponse }
     * 
     */
    public GetPlayersDetailsResponse createGetPlayersDetailsResponse() {
        return new GetPlayersDetailsResponse();
    }

    /**
     * Create an instance of {@link Resign }
     * 
     */
    public Resign createResign() {
        return new Resign();
    }

    /**
     * Create an instance of {@link GameDetails }
     * 
     */
    public GameDetails createGameDetails() {
        return new GameDetails();
    }

    /**
     * Create an instance of {@link Tile }
     * 
     */
    public Tile createTile() {
        return new Tile();
    }

    /**
     * Create an instance of {@link PlayerDetails }
     * 
     */
    public PlayerDetails createPlayerDetails() {
        return new PlayerDetails();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link RummikubFault }
     * 
     */
    public RummikubFault createRummikubFault() {
        return new RummikubFault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinishTurn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "finishTurn")
    public JAXBElement<FinishTurn> createFinishTurn(FinishTurn value) {
        return new JAXBElement<FinishTurn>(_FinishTurn_QNAME, FinishTurn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GameDoesNotExists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "GameDoesNotExists")
    public JAXBElement<GameDoesNotExists> createGameDoesNotExists(GameDoesNotExists value) {
        return new JAXBElement<GameDoesNotExists>(_GameDoesNotExists_QNAME, GameDoesNotExists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEvents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "getEvents")
    public JAXBElement<GetEvents> createGetEvents(GetEvents value) {
        return new JAXBElement<GetEvents>(_GetEvents_QNAME, GetEvents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JoinGame }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "joinGame")
    public JAXBElement<JoinGame> createJoinGame(JoinGame value) {
        return new JAXBElement<JoinGame>(_JoinGame_QNAME, JoinGame.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MoveTile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "moveTile")
    public JAXBElement<MoveTile> createMoveTile(MoveTile value) {
        return new JAXBElement<MoveTile>(_MoveTile_QNAME, MoveTile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateGame }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "createGame")
    public JAXBElement<CreateGame> createCreateGame(CreateGame value) {
        return new JAXBElement<CreateGame>(_CreateGame_QNAME, CreateGame.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWaitingGames }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "getWaitingGames")
    public JAXBElement<GetWaitingGames> createGetWaitingGames(GetWaitingGames value) {
        return new JAXBElement<GetWaitingGames>(_GetWaitingGames_QNAME, GetWaitingGames.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "getEventsResponse")
    public JAXBElement<GetEventsResponse> createGetEventsResponse(GetEventsResponse value) {
        return new JAXBElement<GetEventsResponse>(_GetEventsResponse_QNAME, GetEventsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateGameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "createGameResponse")
    public JAXBElement<CreateGameResponse> createCreateGameResponse(CreateGameResponse value) {
        return new JAXBElement<CreateGameResponse>(_CreateGameResponse_QNAME, CreateGameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlayersDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "getPlayersDetails")
    public JAXBElement<GetPlayersDetails> createGetPlayersDetails(GetPlayersDetails value) {
        return new JAXBElement<GetPlayersDetails>(_GetPlayersDetails_QNAME, GetPlayersDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGameDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "getGameDetails")
    public JAXBElement<GetGameDetails> createGetGameDetails(GetGameDetails value) {
        return new JAXBElement<GetGameDetails>(_GetGameDetails_QNAME, GetGameDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TakeBackTile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "takeBackTile")
    public JAXBElement<TakeBackTile> createTakeBackTile(TakeBackTile value) {
        return new JAXBElement<TakeBackTile>(_TakeBackTile_QNAME, TakeBackTile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinishTurnResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "finishTurnResponse")
    public JAXBElement<FinishTurnResponse> createFinishTurnResponse(FinishTurnResponse value) {
        return new JAXBElement<FinishTurnResponse>(_FinishTurnResponse_QNAME, FinishTurnResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWaitingGamesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "getWaitingGamesResponse")
    public JAXBElement<GetWaitingGamesResponse> createGetWaitingGamesResponse(GetWaitingGamesResponse value) {
        return new JAXBElement<GetWaitingGamesResponse>(_GetWaitingGamesResponse_QNAME, GetWaitingGamesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResignResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "resignResponse")
    public JAXBElement<ResignResponse> createResignResponse(ResignResponse value) {
        return new JAXBElement<ResignResponse>(_ResignResponse_QNAME, ResignResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "addTileResponse")
    public JAXBElement<AddTileResponse> createAddTileResponse(AddTileResponse value) {
        return new JAXBElement<AddTileResponse>(_AddTileResponse_QNAME, AddTileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MoveTileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "moveTileResponse")
    public JAXBElement<MoveTileResponse> createMoveTileResponse(MoveTileResponse value) {
        return new JAXBElement<MoveTileResponse>(_MoveTileResponse_QNAME, MoveTileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JoinGameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "joinGameResponse")
    public JAXBElement<JoinGameResponse> createJoinGameResponse(JoinGameResponse value) {
        return new JAXBElement<JoinGameResponse>(_JoinGameResponse_QNAME, JoinGameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidParameters }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "InvalidParameters")
    public JAXBElement<InvalidParameters> createInvalidParameters(InvalidParameters value) {
        return new JAXBElement<InvalidParameters>(_InvalidParameters_QNAME, InvalidParameters.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlayerDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "getPlayerDetails")
    public JAXBElement<GetPlayerDetails> createGetPlayerDetails(GetPlayerDetails value) {
        return new JAXBElement<GetPlayerDetails>(_GetPlayerDetails_QNAME, GetPlayerDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSequence }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "createSequence")
    public JAXBElement<CreateSequence> createCreateSequence(CreateSequence value) {
        return new JAXBElement<CreateSequence>(_CreateSequence_QNAME, CreateSequence.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TakeBackTileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "takeBackTileResponse")
    public JAXBElement<TakeBackTileResponse> createTakeBackTileResponse(TakeBackTileResponse value) {
        return new JAXBElement<TakeBackTileResponse>(_TakeBackTileResponse_QNAME, TakeBackTileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DuplicateGameName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "DuplicateGameName")
    public JAXBElement<DuplicateGameName> createDuplicateGameName(DuplicateGameName value) {
        return new JAXBElement<DuplicateGameName>(_DuplicateGameName_QNAME, DuplicateGameName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateGameFromXMLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "createGameFromXMLResponse")
    public JAXBElement<CreateGameFromXMLResponse> createCreateGameFromXMLResponse(CreateGameFromXMLResponse value) {
        return new JAXBElement<CreateGameFromXMLResponse>(_CreateGameFromXMLResponse_QNAME, CreateGameFromXMLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Resign }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "resign")
    public JAXBElement<Resign> createResign(Resign value) {
        return new JAXBElement<Resign>(_Resign_QNAME, Resign.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlayersDetailsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "getPlayersDetailsResponse")
    public JAXBElement<GetPlayersDetailsResponse> createGetPlayersDetailsResponse(GetPlayersDetailsResponse value) {
        return new JAXBElement<GetPlayersDetailsResponse>(_GetPlayersDetailsResponse_QNAME, GetPlayersDetailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateGameFromXML }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "createGameFromXML")
    public JAXBElement<CreateGameFromXML> createCreateGameFromXML(CreateGameFromXML value) {
        return new JAXBElement<CreateGameFromXML>(_CreateGameFromXML_QNAME, CreateGameFromXML.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidXML }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "InvalidXML")
    public JAXBElement<InvalidXML> createInvalidXML(InvalidXML value) {
        return new JAXBElement<InvalidXML>(_InvalidXML_QNAME, InvalidXML.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "addTile")
    public JAXBElement<AddTile> createAddTile(AddTile value) {
        return new JAXBElement<AddTile>(_AddTile_QNAME, AddTile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlayerDetailsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "getPlayerDetailsResponse")
    public JAXBElement<GetPlayerDetailsResponse> createGetPlayerDetailsResponse(GetPlayerDetailsResponse value) {
        return new JAXBElement<GetPlayerDetailsResponse>(_GetPlayerDetailsResponse_QNAME, GetPlayerDetailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGameDetailsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "getGameDetailsResponse")
    public JAXBElement<GetGameDetailsResponse> createGetGameDetailsResponse(GetGameDetailsResponse value) {
        return new JAXBElement<GetGameDetailsResponse>(_GetGameDetailsResponse_QNAME, GetGameDetailsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSequenceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rummikub.ws/", name = "createSequenceResponse")
    public JAXBElement<CreateSequenceResponse> createCreateSequenceResponse(CreateSequenceResponse value) {
        return new JAXBElement<CreateSequenceResponse>(_CreateSequenceResponse_QNAME, CreateSequenceResponse.class, null, value);
    }

}
