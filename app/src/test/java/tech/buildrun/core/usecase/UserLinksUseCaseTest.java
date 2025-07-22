package tech.buildrun.core.usecase;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.buildrun.core.domain.Link;
import tech.buildrun.core.domain.LinkFilter;
import tech.buildrun.core.domain.PaginatedResult;
import tech.buildrun.core.exception.FilterException;
import tech.buildrun.core.port.out.LinkRepositoryPortOut;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserLinksUseCaseTest {

    @Mock
    LinkFilter linkFilter;

    @Mock
    LinkRepositoryPortOut linkRepositoryPortOut;

    @InjectMocks
    UserLinksUseCase userLinksUseCase;

    @Nested
    class execute {

        @Test
        void shouldCallFindAllByUserId() {

            // Arrange - preparar todos os mocks,stubs,variaveis
            PaginatedResult<Link> userLinks = new PaginatedResult<>(new ArrayList<>(), null, false);
            String userId = UUID.randomUUID().toString();
            String nextToken = "asdadas";
            int limit = 10;
            doNothing().when(linkFilter).validate();
            doReturn(userLinks).when(linkRepositoryPortOut).findAllByUserId(userId, nextToken, limit, linkFilter);

            // Act - executa o metodo a ser testado
            var output = userLinksUseCase.execute(userId, nextToken, limit, linkFilter);

            // Assert - verificar se o teste teve o comportamento esperado
            verify(linkRepositoryPortOut, times(1))
                    .findAllByUserId(userId, nextToken, limit, linkFilter);
            assertEquals(userLinks, output);
        }

        @Test
        void shouldNotCallFindAllByUserIdWhenFilterException() {

            // Arrange - preparar todos os mocks,stubs,variaveis
            String userId = UUID.randomUUID().toString();
            String nextToken = "asdadas";
            int limit = 10;
            doThrow(FilterException.class).when(linkFilter).validate();

            // Act & Assert - executa o metodo a ser testado
            assertThrows(FilterException.class,
                    () -> userLinksUseCase.execute(userId, nextToken, limit, linkFilter));

            //Assert  - verificar se o teste teve o comportamento esperado
            verify(linkRepositoryPortOut, times(0))
                    .findAllByUserId(anyString(), anyString(), anyInt(), any(LinkFilter.class));
        }


    }
  
}