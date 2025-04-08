package tech.calado.controle_de_estoque.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;
import static tech.calado.controle_de_estoque.item.Grupo.LIMPEZA;
import static tech.calado.controle_de_estoque.item.Unidade.UN;

public class ItemServiceTest {

    @Mock
    private ItemRepository repository;

    private ItemService service;

    @BeforeEach
    void setup () {
        MockitoAnnotations.openMocks(this);
        service = new ItemService(repository);
    }

    @Test
    void should_create_item() {
        Item item = Item.of(randomUUID(), "00001", "Detergente Ype 500ML", LIMPEZA, UN, 1, 2.33);

        when(repository.existsByCodigo(item.getCodigo())).thenReturn(false);
        when(repository.save(item)).thenReturn(item);

        assertThat(service.create(item)).isEqualTo(item);

        InOrder inOrder = inOrder(repository);
        inOrder.verify(repository).existsByCodigo(item.getCodigo());
        inOrder.verify(repository).save(item);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_throw_runtime_exception_when_item_with_given_code_already_exists() {
        Item item = Item.of(randomUUID(), "00001", "Detergente Ype 500ML", LIMPEZA, UN, 1, 2.33);

        when(repository.existsByCodigo(item.getCodigo())).thenReturn(true);

        assertThatThrownBy(() -> service.create(item)).isExactlyInstanceOf(RuntimeException.class);

        InOrder inOrder = inOrder(repository);
        inOrder.verify(repository).existsByCodigo(item.getCodigo());
        inOrder.verifyNoMoreInteractions();
    }

}
