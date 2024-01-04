// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

enum class Sexo { MASCULINO, FEMININO, OUTROS }

class Usuario( var nome: String, var email: String, var nivel: Nivel = Nivel.BASICO, var sexo: Sexo = Sexo.OUTROS ) {

	init {
		if (nome.isEmpty()) throw Exception("Não é possível criar um usuário sem nome.")
		if (email.isEmpty()) throw Exception("Não é possível criar um usuário sem e-mail.")
	}

	fun fazerUpgradePara(nivel: Nivel) {
		this.nivel = nivel
		println("Upgrade realizado com sucesso!")
	}

	fun fazerDowngradePara(nivel: Nivel) {
		this.nivel = nivel
		println("Downgrade realizado com sucesso!")
	}

	fun fazerUpgradeParaProximoNivel() {
		nivel = when(nivel) {
			Nivel.BASICO -> Nivel.INTERMEDIARIO
			Nivel.INTERMEDIARIO -> Nivel.DIFICIL
			else -> nivel
		}
		println("Upgrade realizado com sucesso!")
	}

	fun fazerDowngradeParaNivelAnterior() {
		nivel = when(nivel) {
			Nivel.DIFICIL -> Nivel.INTERMEDIARIO
			Nivel.INTERMEDIARIO -> Nivel.BASICO
			else -> nivel
		}
		println("Downgrade realizado com sucesso!")
	}
}

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

	init {
		if (nome.isEmpty()) throw Exception("Não é possível criar uma formação sem nome.")
		if (conteudos.isEmpty()) throw Exception("Não é possível criar uma formação sem conteúdos.")
	}

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
		inscritos.add(usuario)
		println("${usuario.nome} foi desmatriculado com sucesso!")
		if (inscritos.size > 0) println("Inscritos: ${inscritos.size}")
		print("Inscritos: ")
		println(inscritos.joinToString { it.nome } )
    }

	fun desmatricular(usuario: Usuario) {
		inscritos.remove(usuario)
		println("${usuario.nome} foi desmatriculado com sucesso!")
		if (inscritos.size > 0) println("Inscritos: ${inscritos.size}")
		print("Inscritos: ")
		println(inscritos.joinToString { it.nome } )
		
	}
}

fun main() {
	val usuario = Usuario("Daniel", "teste@teste.com, sexo = sexo.MASCULINO")
	val usuario2 = Usuario("Elson", "teste1@teste.com")
	val curso = Formacao("Curso Kotlin", listOf(ConteudoEducacional("Aula 1"), ConteudoEducacional("Aula 2")))
	curso.matricular(usuario)
	curso.matricular(usuario2)
	println(usuario.nivel)
	usuario.fazerUpgradePara(Nivel.INTERMEDIARIO)
	println(usuario.nivel)
	usuario.fazerUpgradeParaProximoNivel()
	println(usuario.nivel)
	usuario.fazerDowngradeParaNivelAnterior()
	println(usuario.nivel)
	curso.desmatricular(usuario)
	// val usuario3 = Usuario("", "")
	// val curso2 = Formacao("", listOf())
	// curso2.matricular(usuario3)
}
