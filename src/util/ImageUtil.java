package util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUtil {
	//Configura imagem para usar nos botões, retorn imageView
	public static ImageView configurarImagem(String caminho,double altura, double largura) {
    	Image image = new Image(ImageUtil.class.getResourceAsStream(caminho));
    	ImageView imageView = new ImageView(image);
    	imageView.setFitHeight(altura);
    	imageView.setFitWidth(largura);
    	
    	return imageView;   	
	}
	
	public static Image configurarImagemNormal(String caminho) {
		Image imagem = new Image(ImageUtil.class.getResourceAsStream(caminho));
		return imagem;
	}
}
