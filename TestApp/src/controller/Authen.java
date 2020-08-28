package controller;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import modal.Account;
import modal.TongHop;

@Controller
@Repository
@Transactional
public class Authen {

	@Autowired
	SessionFactory sessionFactory;
	String username = "";

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String goToLogin(ModelMap model) {
		model.addAttribute("acc", new Account());
		return "/login";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String authen(ModelMap model, Account acc) {
		try {
			String userName = acc.getAccount();
			String passWord = acc.getPwd();
			String sql = "select * from member where account = '" + userName + "' and pwd = '" + passWord + "'";
			System.out.println(sql);
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.addEntity(Account.class);
			ArrayList<Account> rs = (ArrayList<Account>) query.list();
			System.out.println(rs.size());
			if (rs.size() == 1) {
				username = rs.get(0).getAccount();
				return "redirect:/index";
			}
			return "/404";
		} catch (Exception e) {
			e.printStackTrace();
			return "/404";
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String goToIndex(HttpSession session, ModelMap model) {
		try {
			session.setAttribute("username", username);
			String sql = "select cart.id, member.account, product.productName, cart.statusCart from cart join product on cart.idProduct = product.id join member on cart.idMember = member.id";
			System.out.println(sql);
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(TongHop.class);
//			System.out.println(query.list().size());
//			query.addEntity(TongHop.class);
			ArrayList<TongHop> list = (ArrayList<TongHop>) query.list();
			model.addAttribute("listInfo", list.size());
			return "/login";
		} catch (Exception e) {
			e.printStackTrace();
			return "/404";
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, ModelMap model) {
		username = "";
		session.setAttribute("username", username);
		return "redirect:/";
	}

	public static void main(String[] args) {
//		SessionFactory sessionFactory = null;
		System.out.println("OKEY");
//		System.out.println(sessionFactory.getCurrentSession().get(Account.class, 1));
	}

}
