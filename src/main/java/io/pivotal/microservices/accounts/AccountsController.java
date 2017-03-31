package io.pivotal.microservices.accounts;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.microservices.as400.AdapterAtrJava;
import io.pivotal.microservices.as400.CuentaParser;
import io.pivotal.microservices.as400.Iparser;
import io.pivotal.microservices.exceptions.AccountNotFoundException;
import com.scotiabank.atr.AtrJava;

/**
 * A RESTFul controller for accessing account information.
 * 
 * @author Paul Chapman
 */
@RestController
public class AccountsController {

	protected Logger logger = Logger.getLogger(AccountsController.class
			.getName());
	protected AccountRepository accountRepository;
	protected Iparser parser;
	protected AdapterAtrJava adapterAtrJava;

	/**
	 * Create an instance plugging in the respository of Accounts.
	 * 
	 * @param accountRepository
	 *            An account repository implementation.
	 */
	@Autowired
	public AccountsController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
		this.parser = new CuentaParser();
		this.adapterAtrJava = new AdapterAtrJava();
		logger.info("AccountRepository says system has "
				+ accountRepository.countAccounts() + " accounts");
	}

	/**
	 * Fetch an account with the specified account number.
	 * 
	 * @param accountNumber
	 *            A numeric, 9 digit account number.
	 * @return The account if found.
	 * @throws AccountNotFoundException
	 *             If the number is not recognised.
	 */
	@RequestMapping("/accounts/{accountNumber}")
	public Account byNumber(@PathVariable("accountNumber") String accountNumber) {

		logger.info("accounts-service byNumber() invoked: " + accountNumber);
		Account account = accountRepository.findByNumber(accountNumber);
		logger.info("accounts-service byNumber() found: " + account);

		if (account == null)
			throw new AccountNotFoundException(accountNumber);
		else {
			return account;
		}
	}
	@RequestMapping(value = "/CuentaCorriente/{cta}", method = RequestMethod.GET, produces = "application/json")
	public CuentaParser AS400query(@PathVariable("cta") String cta) {				
		parser.traslateInput(cta);
		logger.info("merge");
		AtrJava atr= adapterAtrJava.getAdapterAtrJava();
		int rc = atr.callService(parser.getNombreServicio(), parser.getNombreUsuario(), parser.getNombreAplicacion(), parser.getTimeout(), parser.getInput().length(),parser.getInput() );
	    parser.setOutput(atr.getRequest());
	    CuentaParser cuenta= parser.traslateOutput();
		logger.info("mergeA");
		logger.info("mergeB");
		logger.info("mergeC");
		return cuenta;
	}

	/**
	 * Fetch accounts with the specified name. A partial case-insensitive match
	 * is supported. So <code>http://.../accounts/owner/a</code> will find any
	 * accounts with upper or lower case 'a' in their name.
	 * 
	 * @param partialName
	 * @return A non-null, non-empty set of accounts.
	 * @throws AccountNotFoundException
	 *             If there are no matches at all.
	 */
	@RequestMapping("/accounts/owner/{name}")
	public List<Account> byOwner(@PathVariable("name") String partialName) {
		logger.info("accounts-service byOwner() invoked: "
				+ accountRepository.getClass().getName() + " for "
				+ partialName);

		List<Account> accounts = accountRepository
				.findByOwnerContainingIgnoreCase(partialName);
		logger.info("accounts-service byOwner() found: " + accounts);

		if (accounts == null || accounts.size() == 0)
			throw new AccountNotFoundException(partialName);
		else {
			return accounts;
		}
	}
}
