package pannelli;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ComponenteRispostaQuiz{
	JTextField txtRisposta;
	JRadioButton rdbtnRadioButton;
	JCheckBox chckbxCheckBox;
	JButton btnEliminaRisposta;
	int id;
	
	public ComponenteRispostaQuiz(JTextField txtRisposta, JRadioButton rdbtnRadioButton, JCheckBox chckbxCheckBox,
			JButton btnEliminaRisposta, int id) {
		super();
		this.txtRisposta = txtRisposta;
		this.rdbtnRadioButton = rdbtnRadioButton;
		this.chckbxCheckBox = chckbxCheckBox;
		this.btnEliminaRisposta = btnEliminaRisposta;
		this.id = id;
	}
	public JTextField getTxtRisposta() {
		return txtRisposta;
	}
	public void setTxtRisposta(JTextField txtRisposta) {
		this.txtRisposta = txtRisposta;
	}
	public JRadioButton getRdbtnRadioButton() {
		return rdbtnRadioButton;
	}
	public void setRdbtnRadioButton(JRadioButton rdbtnRadioButton) {
		this.rdbtnRadioButton = rdbtnRadioButton;
	}
	public JCheckBox getChckbxCheckBox() {
		return chckbxCheckBox;
	}
	public void setChckbxCheckBox(JCheckBox chckbxCheckBox) {
		this.chckbxCheckBox = chckbxCheckBox;
	}
	public JButton getBtnEliminaRisposta() {
		return btnEliminaRisposta;
	}
	public void setBtnEliminaRisposta(JButton btnEliminaRisposta) {
		this.btnEliminaRisposta = btnEliminaRisposta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
