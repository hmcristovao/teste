package negocio;

import java.sql.Date;

public class Data {
	private int dia, mes, ano;

	public Data(int _mes, int _ano) throws Exception {
		this(1, _mes, _ano);
	}
	public Data(int _dia, int _mes, int _ano) throws Exception {
		this.setData(_dia, _mes, _ano);
	}
	public Data(String _data) throws Exception {
		this.setData(_data);
	}
	public Data(int _dia, String _mes, int _ano) throws Exception {
		this(_dia, Data.retornaMes(_mes), _ano);
	}
	public Data() {
		try {
			this.setData(1, 1, 1900);
		} catch (Exception e) {

		}
	}
	// método usado para auxiliar a leitura de datas em tabelas do MySQL
	public Data(Date date) throws Exception {
		this.setData(date.getDay(), date.getMonth()+1, date.getYear()+1900);
	}
	public void setData(int _dia, int _mes, int _ano) throws Exception {
		if (Data.isDataValida(_dia, _mes, _ano)) {
			this.dia = _dia;
			this.mes = _mes;
			this.ano = _ano;
		} else {
			throw new Exception("Data inválida");
		}
	}
	public void setData(String _data) throws Exception {
		int firstindex = _data.indexOf("/");
		int secindex = _data.indexOf("/", firstindex + 1);
		this.setData(Integer.parseInt(_data.substring(0, firstindex)),
				Integer.parseInt(_data.substring((firstindex + 1), secindex)),
				Integer.parseInt(_data.substring((secindex + 1), (secindex + 5))));
	}
	public int getDia() {
		return this.dia;
	}
	public int getMes() {
		return this.mes;
	}
	public int getAno() {
		return this.ano;
	}
	public static boolean isDataValida(int _dia, int _mes, int _ano) {
		if (_ano < 1582) {
			return false;
		}
		if (_mes > 12) {
			return false;
		}
		if (_mes == 1 || _mes == 3 || _mes == 5 || _mes == 7 || _mes == 8 || _mes == 10 || _mes == 12) {
			if (_dia > 31 || _dia < 1) {
				return false;
			}
		} else if (_mes == 2) {
			if (Data.isBissexto(_ano)) {
				if (_dia > 29 || _dia < 1) {
					return false;
				}
			} else {
				if (_dia > 28 || _dia < 1) {
					return false;
				}
			}
		} else {
			if (_dia > 30 || _dia < 1) {
				return false;
			}
		}
		return true;
	}
	public boolean isDataValida(int _dia, String _mes, int _ano) {
		if (Data.isDataValida(_dia, Data.retornaMes(_mes), _ano))
			return true;
		return false;
	}
	private static boolean isBissexto(int _ano) {
		if ((_ano % 4 == 0 && _ano % 100 != 0) || _ano % 400 == 0)
			return true;
		return false;
	}
	public static int retornaMes(String _mes) {
		_mes = _mes.toLowerCase();
		switch (_mes) {
		case "janeiro":
			return 1;
		case "fevereiro":
			return 2;
		case "março":
			return 3;
		case "marco":
			return 3;
		case "abril":
			return 4;
		case "maio":
			return 5;
		case "junho":
			return 6;
		case "julho":
			return 7;
		case "agosto":
			return 8;
		case "setembro":
			return 9;
		case "outubro":
			return 10;
		case "novembro":
			return 11;
		case "dezembro":
			return 12;
		default:
			return 13;
		}
	}
	public Data incrementa() {
		try {
			this.setData(this.getDia() + 1, this.getMes(), this.getAno());
		} catch (Exception e) {
			try {
				this.setData(1, this.getMes() + 1, this.getAno());
			} catch (Exception ex) {
				try {
					this.setData(1, 1, this.getAno() + 1);
				} catch (Exception exc) {
				}
			}
		}
		return this;
	}
	public Data incrementa(int _quantidade) {
		for (int i = 1; i < _quantidade; i++) {
			this.incrementa();
		}
		return this;
	}
	public boolean equals(Object objeto) {
		Data aux = (Data) objeto;
		if (this.getDia() == aux.getDia() && this.getMes() == aux.getMes() && this.getAno() == aux.getAno()) {
			return true;
		}
		return false;
	}
	public static Data verificaECriaData(int _dia, int _mes, int _ano) throws Exception {
		if (Data.isDataValida(_dia, _mes, _ano)) {
			Data dt = new Data(_dia, _mes, _ano);
			return dt;
		} else
			return null;
	}
	public int compareTo(Data _data) {
		return Data.compareTo(this.getDia(), this.getMes(), this.getAno(), _data.getDia(), _data.getMes(), _data.getAno());
	}
	public static int compareTo(int _dia1, int _mes1, int _ano1, int _dia2, int _mes2, int _ano2) {
		if (_ano1 > _ano2) {
			return -1;
		} else if (_ano1 < _ano2) {
			return 1;
		} else {
			if (_mes1 > _mes2) {
				return -1;
			} else if (_mes1 < _mes2) {
				return 1;
			} else {
				if (_dia1> _dia2) {
					return -1;
				} else if (_dia1 < _dia2) {
					return 1;
				} else
					return 0;
			}
		}
	}
	// formato AAAA-MM-DD
	public String toString() {
		StringBuilder data = new StringBuilder();
		data.append(this.getAno());
		data.append("-");
		data.append(this.getMes());
		data.append("-");
		data.append(this.getDia());
		return data.toString();
	}
}
