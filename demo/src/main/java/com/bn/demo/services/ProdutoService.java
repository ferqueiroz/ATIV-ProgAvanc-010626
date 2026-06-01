package com.bn.demo.services;

import com.bn.demo.models.ProdutoModel;
import com.bn.demo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Define que essa classe será um service onde é definido as regras de negócios dos endpoints
public class ProdutoService implements UserDetailsService {

    @Autowired // Mesma coisa explicada no controller
    private ProdutoRepository produtoRepository;

    public List<ProdutoModel> buscarTodosProdutos(){
        return  produtoRepository.findAll();
    }

    public ProdutoModel criarProduto(ProdutoModel produtoModel){
        return  produtoRepository.save(produtoModel);
    }

    public Optional<ProdutoModel> buscarProdutoId(Long id){
        return  produtoRepository.findById(id);
    }

    public ProdutoModel atualizarProduto(Long id, ProdutoModel produtoModel){
        ProdutoModel model = produtoRepository.findById(id).get();
        model.setPreco(produtoModel.getPreco());
        model.setEstoque(produtoModel.getEstoque());
        model.setNome(produtoModel.getNome());
        return produtoRepository.save(model);
    }

    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }

    @Override // Sobrescreve um metodo da superclasse
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return produtoRepository.findByLogin(username);
    }
}
