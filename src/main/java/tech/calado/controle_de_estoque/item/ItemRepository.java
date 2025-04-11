package tech.calado.controle_de_estoque.item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface ItemRepository extends CrudRepository<Item, UUID> {

	boolean existsByCodigo(String codigo);

}
