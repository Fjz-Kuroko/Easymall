package easymall.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.ProductsDao;
import easymall.po.Category;
import easymall.po.Products;
import easymall.pojo.MyProducts;

@Service("productsService")
public class ProductsServiceImpl implements ProductsService {
	
	@Autowired
	private ProductsDao productsDao;

	@Override
	public List<Category> allcategorys() {
		return productsDao.allcategorys();
	}

	@Override
	public List<Products> prodlist(Map<String, Object> map) {
		return productsDao.prodlist(map);
	}

	@Override
	public Products oneProduct(String pid) {
		return productsDao.oneProduct(pid);
	}

	@Override
	public List<Products> proclass(Integer proclass) {
		return productsDao.proclass(proclass);
	}

	@Override
	public String save(MyProducts myProducts, HttpServletRequest request) {
		// 1.�жϺ�׺�Ƿ�Ϸ�
		// ��ȡͼƬ���ƣ���׺����
		String originName = myProducts.getImgurl().getOriginalFilename();
		
		// ��ȡ��׺
		String extName = originName.substring(originName.lastIndexOf("."));
		
		if (!(extName.equalsIgnoreCase(".jpg") || extName.equalsIgnoreCase(".png") || extName.equalsIgnoreCase(".gif"))) {
			return "ͼƬ��׺���Ϸ���";
		}
		// �ж�ľ��
		try{
			BufferedImage bufImage = ImageIO.read(myProducts.getImgurl().getInputStream());
			bufImage.getHeight();
			bufImage.getWidth();
		} catch(Exception e) {
			return "���ļ�����ͼƬ��";
		}
		// 2.����upload��ʼ��һ��·��
		// ���ɶ༶·��
		String imgurl = "";
		for (int i=0; i<8;i++) {
			imgurl += "/" + Integer.toHexString(new Random().nextInt(16));
		}
		String realpath = request.getServletContext().getRealPath("/WEB-INF");
		realpath += "/upload";
		System.out.println(realpath + imgurl);
		File file = new File(realpath + imgurl, originName);
		if (!file.exists()) {
			file.mkdirs();
		}
		// �ϴ��ļ�
		try {
			myProducts.getImgurl().transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ƴ��ͼƬ�������ݿ��·��
		imgurl = "/upload" + imgurl + "/" + originName;
		String id = UUID.randomUUID().toString();
		Products products = new Products();
		products.setId(id);
		products.setName(myProducts.getName());
		products.setCategory(myProducts.getCategory());
		products.setPrice(myProducts.getPrice());
		products.setPnum(myProducts.getPnum());
		products.setImgurl(imgurl);
		products.setDescription(myProducts.getDescription());
		if (productsDao.findByImgurl(products.getImgurl()) == null) {
			productsDao.save(products);
		} else {
			String fname = imgurl.substring(0, imgurl.lastIndexOf("."));
			imgurl = fname + System.currentTimeMillis() + extName;
			products.setImgurl(imgurl);
			System.out.println(products.getImgurl());
			productsDao.save(products);
		}
		return "��Ʒ��ӳɹ�";
	}

}
