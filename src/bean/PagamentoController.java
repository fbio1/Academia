package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.PagamentoDaoImpl;
import model.Pagamento;

@ManagedBean
@SessionScoped
public class PagamentoController {
	private Pagamento pagamento;
	private PagamentoDaoImpl pagamentodao;
}
