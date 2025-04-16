package tech.calado.controle_de_estoque.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface ItemRepository extends JpaRepository<Item, UUID> {

	boolean existsByCodigo(String codigo);

}
