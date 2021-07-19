package br.edu.unoesc.fdj.coronapp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Patch;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.edu.unoesc.fdj.coronapp.model.Paciente;
import br.edu.unoesc.fdj.coronapp.model.Sintoma;
import br.edu.unoesc.fdj.coronapp.service.PacienteService;

@Controller
public class PacienteController {
	@Autowired
	private PacienteService pacienteService;
	
    //caminho onde vai ficar salvo as imagens
	private static String caminhoImagens = "C:\\imagens/";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
	}

	@RequestMapping("/buscar")
	public String buscar(@RequestParam(required = false) Long cpf, HttpSession session, Model model) {
		if (cpf == null)
			cpf = (Long) session.getAttribute("cpf");
		else
			session.setAttribute("cpf", cpf);

		Paciente paciente = pacienteService.listarPorCPF(cpf);

		if (paciente != null) {
			model.addAttribute("paciente", paciente);
		}

		List<Sintoma> sintomas = Arrays.asList(Sintoma.values());
		model.addAttribute("sintomas", sintomas);

		return "paciente";
	}

	// atividade 1
	private boolean validarCpf(@Valid Long cpf, BindingResult erros) {
		if (erros.hasErrors()) {
			return false;
		} else {
			return true;
		}
	}

	@PostMapping("/testar")
	public String testar(@Valid Paciente paciente, @RequestParam String[] sintomas, BindingResult erros,
			HttpSession session, @RequestParam("file") MultipartFile arquivo) {
		if (erros.hasErrors()) {
			return "paciente";
		}

		// calcular as chances de estar com o coronavírus
		double resultado = 0.0;
		for (int i = 0; i < sintomas.length; i++) {
			resultado += Double.parseDouble(sintomas[i]);
		}
		paciente.setResultado(resultado);

		try {
			if (validarCpf(paciente.getCpf(), erros)) {
				pacienteService.salvar(paciente);
				
				try {
					if(!arquivo.isEmpty()) {
						byte[] bytes = arquivo.getBytes();
						Path caminho = Paths.get(caminhoImagens + arquivo.getOriginalFilename());
						Files.write(caminho, bytes);
						String cam = caminhoImagens + arquivo.getOriginalFilename();
						paciente.setImagem("teste");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				return "redirect:exibir";
			} else {
				session.setAttribute("erro", "CPF inválido!");
				return "redirect:buscar";
			}
		} catch (Exception e) {
			session.setAttribute("erro", "Erro ao tentar salvar teste do paciente!" + e.getMessage());
			return "redirect:buscar";
		}

	}

	@RequestMapping("/exibir")
	public String exibir(HttpSession session, Model model) {
		Long cpf = (Long) session.getAttribute("cpf");
		if (cpf == null)
			return "/";
		else {
			Paciente paciente = pacienteService.listarPorCPF(cpf);
			model.addAttribute("paciente", paciente);
			return "resultado";
		}

	}

	@GetMapping("/rest/listarporresultado/{resultado}")
	@ResponseBody
	public List<Paciente> listarPorResultado(@PathVariable("resultado") Double resultado) {
		return pacienteService.listarPorResultado(resultado);
	}

	@GetMapping("/rest/listarporsexo/{sexo}")
	@ResponseBody
	public List<Paciente> listarPorSexo(@PathVariable("sexo") String sexo) {
		return pacienteService.listarPorSexo(sexo);
	}

	// atividade 3
	@RequestMapping("/buscacpf/{cpf}")
	public String exibirListaCpf(@PathVariable int cpf, Model model) {
		Paciente paciente = pacienteService.listarPorCPF((long) cpf);

		if (paciente != null) {
			model.addAttribute("mensagemcpf", "Este CPF " + cpf + " existe na base de dados.");
			model.addAttribute("dadospessoa", "Nome: " + paciente.getNome() + " - Nasc: "
					+ paciente.getDataNascimentoFmt() + " - Result: " + paciente.getResultado());
		} else {
			model.addAttribute("mensagemcpf", "CPF " + cpf + " NÃO FOI ENCONTRADO na base de dados.");
		}
		return "atividade";
	}

	// atividade 5
	@RequestMapping("/listaporoitenta/{valor}")
	public String listaPorOitenta(@PathVariable double valor, Model model) {

		List<Paciente> paciente = pacienteService.listaResultPorValor((double) valor);
		model.addAttribute("listapaciente", paciente);

		return "atividade";
	}

	@RequestMapping(value = "/listapacientes", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public String listapacientes(Model model) {
		List<Paciente> paciente = pacienteService.listar();
		model.addAttribute("listapaciente", paciente);
		return "atividade";
	}

}