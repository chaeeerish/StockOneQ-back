package umc.stockoneqback.common;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import umc.stockoneqback.admin.domain.StaticFARedisRepository;
import umc.stockoneqback.auth.domain.repository.FcmTokenRedisRepository;
import umc.stockoneqback.auth.domain.repository.RefreshTokenRedisRepository;
import umc.stockoneqback.board.domain.BoardRepository;
import umc.stockoneqback.board.domain.like.BoardLikeRepository;
import umc.stockoneqback.board.domain.views.ViewsRedisRepository;
import umc.stockoneqback.business.domain.BusinessRepository;
import umc.stockoneqback.comment.domain.repository.CommentRepository;
import umc.stockoneqback.field.domain.repository.CompanyRepository;
import umc.stockoneqback.field.domain.repository.PartTimerRepository;
import umc.stockoneqback.field.domain.repository.StoreRepository;
import umc.stockoneqback.friend.domain.repository.FriendRepository;
import umc.stockoneqback.product.domain.repository.ProductRepository;
import umc.stockoneqback.reply.domain.repository.ReplyRepository;
import umc.stockoneqback.share.domain.repository.ShareRepository;
import umc.stockoneqback.user.domain.UserRepository;

@SpringBootTest
@Transactional
@Import(EmbeddedRedisConfig.class)
public class ServiceTest {
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private RedisCleaner redisCleaner;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected StoreRepository storeRepository;

    @Autowired
    protected CompanyRepository companyRepository;

    @Autowired
    protected PartTimerRepository partTimerRepository;

    @Autowired
    protected BusinessRepository businessRepository;
  
    @Autowired
    protected BoardRepository boardRepository;

    @Autowired
    protected RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Autowired
    protected CommentRepository commentRepository;

    @Autowired
    protected ReplyRepository replyRepository;
  
    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected FriendRepository friendRepository;

    @Autowired
    protected BoardLikeRepository boardLikeRepository;

    @Autowired
    protected StaticFARedisRepository StaticFARedisRepository;

    @Autowired
    protected ShareRepository shareRepository;

    @Autowired
    protected FcmTokenRedisRepository fcmTokenRedisRepository;

    @Autowired
    protected ViewsRedisRepository viewsRedisRepository;

    public void flushAndClear() {
        databaseCleaner.flushAndClear();
    }

    @BeforeEach
    void setUp() {
        databaseCleaner.execute();
        redisCleaner.flushAll();
    }
}
