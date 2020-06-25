
import java.util.ArrayList;
import java.util.Scanner;

public class StudentBiz {
	Scanner input = new Scanner(System.in);
	// 人员数据存储的数组
	ArrayList<student> stulist = new ArrayList<student>();
	ArrayList<adminer> admlist = new ArrayList<adminer>();

	// 存储当前登录用户的对象地址
	int nowuser;
	String nowident = "";

	// 初始化人员
	public void initList() {
		stulist.add(new student("1", "小明", 100, 80, 80));
		stulist.add(new student("2", "小红", 100, 100, 100));
		stulist.add(new student("3", "小刚", 80, 100, 80));
		stulist.add(new student("4", "张三", 40, 40, 40));
		admlist.add(new adminer("root", "123456"));
	}

	// 登陆界面
	public void menu() {
		System.out.println("======欢迎使用学生管理系统======");
		System.out.println("      	    1.登陆");
		System.out.println("      	    2.注册(管理员)");
		System.out.println("      	    3.退出");
		System.out.println("================================");
		System.out.print("->");
		int key = input.nextInt();
		switch (key) {
		case 1:
			login();
			break;
		case 2:
			register();
			break;
		case 3:
			System.exit(0);
			break;
		default:
			System.out.println("警告：输入不明字符，程序已退出。");
			System.exit(0);
			break;
		}
	}

	// 登陆页面
	public void login() {
		System.out.println("======欢迎登陆学生管理系统======");
		System.out.print("用户名（用户名为id）：");
		String username = input.next();
		System.out.print("密码（密码默认为123456）：");
		String pw = input.next();

		// 判断身份
		String identity = "";// 存储身份信息
		for (Object e : stulist) {// 如果用户名学生身份相同 那就是学生
			if ((((student) e).getIdent()).equals("stu") && ((((student) e).getSid())).equals(username)) {
				identity = "stu";
			}
		}
		for (Object e : admlist) {// 如果用户名管理员身份相同 那就是管理员
			if ((((adminer) e).getIdent()).equals("adm") && ((((adminer) e).getAid())).equals(username)) {
				identity = "adm";
			}
		}
		// 否则identity就是空字符串

		// 验证密码是否相同
		if (identity.equals("stu")) {
			for (int i = 0; i < stulist.size(); i++) {// 找出来id一样的那个人并判断密码是否相同
				if ((stulist.get(i).getSid()).equals(username) && ((stulist.get(i).getPassword()).equals(pw))) {
					System.out.println("登陆成功，欢迎学生登陆本系统。");
					System.out.println("================================\n");
					// 存储当前用户数组下标和身份
					nowuser = i;
					nowident = "stu";
					// 进入学生界面
					stumenu();
				} else if ((stulist.get(i).getSid()).equals(username) && !((stulist.get(i).getPassword()).equals(pw))) {
					System.out.println("密码错误，返回主菜单。");
					System.out.println("================================\n");
					// 进入主菜单
					menu();
				}
			}
		} else if (identity.equals("adm")) {
			for (int i = 0; i < admlist.size(); i++) {// 找出来id一样的那个人并判断密码是否相同
				if ((admlist.get(i).getAid()).equals(username) && ((admlist.get(i).getPassword()).equals(pw))) {
					System.out.println("登陆成功，欢迎管理员登陆本系统。");
					System.out.println("================================\n");
					// 存储当前用户数组下标和身份
					nowuser = i;
					nowident = "adm";
					// 进入管理员界面
					admmenu();
				} else if ((admlist.get(i).getAid()).equals(username) && !(admlist.get(i).getPassword()).equals(pw)) {
					System.out.println("密码错误，返回主菜单。");
					System.out.println("================================\n");
					// 进入主菜单
					menu();
				}
			}
		} else {
			System.out.println("查无此人，请重新输入\n");
			login();
		}
	}

	// 注册页面
	public void register() {
		System.out.println("======欢迎注册学生管理系统======");
		System.out.print("用户名：");
		String un = input.next();
		System.out.print("密码：");
		String pw1 = input.next();
		System.out.print("再输入一次密码：");
		String pw2 = input.next();

		// 用户名去重
		for (Object e : admlist) {//
			if (((((adminer) e).getAid())).equals(un)) {
				System.out.println("用户名已存在，请重新注册。");
				System.out.println("================================\n");
				register();
			}
		}
		// 判断2次密码是否相同
		if (pw1.equals(pw2)) {
			admlist.add(new adminer(un, pw1));

			System.out.println("注册成功，转登陆界面。");
			System.out.println("================================\n");
			login();
		} else {
			System.out.println("两次密码需要一致！请重新注册。");
			System.out.println("================================\n");
			register();
		}

	}

	// 学生界面
	public void stumenu() {
		System.out.println("===========学生界面=============");
		System.out.println("	1.查询所有人成绩");
		System.out.println("	2.查询指定学生成绩");
		System.out.println("	3.修改密码");
		System.out.println("	4.退出");
		System.out.println("================================");
		System.out.print("->");
		int key = input.nextInt();
		switch (key) {
		case 1:
			showDegree();
			stumenu();
			break;
		case 2:
			showTarget();
			stumenu();
			break;
		case 3:
			changePw();
			break;
		case 4:
			System.exit(0);
			break;
		default:
			System.out.println("警告：输入不明字符，系统返回学生界面。");
			stumenu();
			break;
		}
	}

	// 显示所有学生成绩
	public void showDegree() {
		System.out.println("=============学生成绩===============");
		System.out.println("学号\t姓名\t语文\t数学\t英语");
		for (int i = 0; i < stulist.size(); i++) {
			System.out.println(
					stulist.get(i).getSid() + "\t" + stulist.get(i).getSname() + "\t" + stulist.get(i).getChinese()
							+ "\t" + stulist.get(i).getMath() + "\t" + stulist.get(i).getEnglish());
		}
		System.out.println("====================================\n");

	}

	// 显示指定学生成绩
	public void showTarget() {
		System.out.println("==========查询指定学生成绩============");
		System.out.print("输入姓名或学号：");
		String search = input.next();
		System.out.println("学号\t姓名\t语文\t数学\t英语");
		for (int i = 0; i < stulist.size(); i++) {
			if (stulist.get(i).getSid().equals(search) || stulist.get(i).getSname().equals(search)) {
				System.out.println(
						stulist.get(i).getSid() + "\t" + stulist.get(i).getSname() + "\t" + stulist.get(i).getChinese()
								+ "\t" + stulist.get(i).getMath() + "\t" + stulist.get(i).getEnglish());
			}
		}
		System.out.println("====================================\n");
	}

	// 修改密码界面
	public void changePw() {
		System.out.println("=============修改密码===============");
		System.out.print("输入旧密码：");
		String oldpw = input.next();
		System.out.print("输入新密码：");
		String newpw = input.next();
		System.out.print("再次输入新密码：");
		String newpw2 = input.next();

		// 判断是什么用户
		if (nowident.equals("stu")) {
			// 旧密码相同
			if (stulist.get(nowuser).getPassword().equals(oldpw)) {
				// 相同的话继续判断新输入密码和再次输入密码是否相同
				if (newpw.equals(newpw2)) {
					stulist.get(nowuser).setPassword(newpw);
					System.out.println("密码修改成功,请重新登录。");
					System.out.println("====================================\n");
					menu();
				} else {
					System.out.println("两次密码输入不一致，请重新输入");
					System.out.println("====================================\n");
					changePw();
				}
			} else {
				// 旧密码不同
				System.out.println("旧密码输入错误，返回主菜单。");
				System.out.println("====================================\n");
				stumenu();
			}
		} else if (nowident.equals("adm")) {
			// 旧密码相同
			if (admlist.get(nowuser).getPassword().equals(oldpw)) {
				// 相同的话继续判断新输入密码和再次输入密码是否相同
				if (newpw.equals(newpw2)) {
					admlist.get(nowuser).setPassword(newpw);
					System.out.println("密码修改成功,请重新登录。");
					System.out.println("====================================\n");
					menu();
				} else {
					System.out.println("两次密码输入不一致，请重新输入");
					System.out.println("====================================\n");
					changePw();
				}
			} else {
				// 旧密码不同
				System.out.println("旧密码输入错误，返回主菜单。");
				System.out.println("====================================\n");
				stumenu();
			}
		} else {
			System.out.println("不明身份，无法识别。");
			System.out.println("====================================\n");
			System.exit(0);
		}

	}

	// 管理员界面
	public void admmenu() {
		System.out.println("===========管理界面=============");
		System.out.println("	1.查询所有人成绩");
		System.out.println("	2.查询指定学生成绩");
		System.out.println("	3.录入学生");
		System.out.println("	4.修改学生信息");
		System.out.println("	5.删除学生信息");
		System.out.println("	6.修改密码");
		System.out.println("	7.退出");
		System.out.println("================================");
		System.out.print("->");
		int key = input.nextInt();
		switch (key) {
		case 1:
			showDegree();
			admmenu();
			break;
		case 2:
			showTarget();
			admmenu();
			break;
		case 3:
			entryStu();
			break;
		case 4:
			changScore();
			break;
		case 5:
			removeStu();
			break;
		case 6:
			changePw();
			break;
		case 7:
			System.exit(0);
			break;
		case 8:
			Boom();
			break;
		default:
			System.out.println("警告：输入不明字符，系统返回管理界面。");
			admmenu();
			break;
		}
	}

	// 录入学生
	public void entryStu() {
		String choose2 = "y";
		do {
			System.out.println("===========录入学生=============");
			System.out.print("学号：");
			String id = input.next();
			// 学号去重
			for (int i = 0; i < stulist.size(); i++) {
				if (stulist.get(i).getSid().equals(id)) {
					System.out.println("学号重复，请重新录入。");
					System.out.println("================================");
					admmenu();
				}
			}
			System.out.print("姓名：");
			String name = input.next();
			System.out.print("语文：");
			double ch = input.nextDouble();
			System.out.print("数学：");
			double ma = input.nextDouble();
			System.out.print("英语：");
			double en = input.nextDouble();

			stulist.add(new student(id, name, ch, ma, en));
			System.out.print("录入成功,是否继续录入？(y/n)：");
			choose2 = input.next();
			System.out.println("================================");

		} while (choose2.equals("y"));

		admmenu();
	}

	// 修改学生信息
	public void changScore() {
		String choose3 = "y";
		do {
			System.out.println("==========修改指定学生成绩============");
			System.out.print("输入姓名或学号：");
			String search = input.next();
			System.out.println("可修改项：1.姓名 2.语文 3.数学 4.英语");
			System.out.print("输入要修改的项：");
			String key = input.next();
			System.out.print("修改为：");
			String value = input.next();
			int n;
			Boolean nobody = true;
			for (int i = 0; i < stulist.size(); i++) {
				System.out.println("id" + (stulist.get(i).getSid()));
				System.out.println("search" + search);
				if ((stulist.get(i).getSid()).equals(search) || (stulist.get(i).getSname()).equals(search)) {
					nobody = false;
					switch (key) {
					case "1":
						stulist.get(i).setSname(value);
						break;
					case "2":
						n = Integer.parseInt(value);
						stulist.get(i).setChinese(n);
						;
						break;
					case "3":
						n = Integer.parseInt(value);
						stulist.get(i).setMath(n);
						break;
					case "4":
						n = Integer.parseInt(value);
						stulist.get(i).setEnglish(n);
						break;
					default:
						break;
					}
					System.out.print("修改成功，是否继续修改(y/n)：");
					choose3 = input.next();
					admmenu();
					System.out.println("================================");
				}
			}
			if (nobody) {
				System.out.println("查无此人，返回管理菜单。");
				admmenu();
			}
		} while (choose3.equals("y"));

	}

	// 删除学生信息
	public void removeStu() {
		String choose4 = "y";
		String choose5 = "y";
		do {
			System.out.println("==========删除指定学生信息============");
			System.out.print("输入姓名或学号：");
			String search = input.next();
			for (int i = 0; i < stulist.size(); i++) {
				if ((stulist.get(i).getSid()).equals(search) || (stulist.get(i).getSname()).equals(search)) {
					System.out.print("是否确认删除(y/n)？");
					choose5 = input.next();
					if (choose5.equals("n")) {
						admmenu();
						continue;
					}
					stulist.remove(i);
					System.out.print("删除成功，是否继续删除(y/n)：");
					choose4 = input.next();
					System.out.println("================================");
				}
			}
		} while (choose4.endsWith("y"));
		admmenu();
	}

	public static void main(String[] args) {
		StudentBiz biz = new StudentBiz();
		biz.initList();
		biz.menu();
	}

	public void Boom() {
		System.out.println("自爆程序已启动");
		for (int i = 10; i >= 1; i--) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 1000毫秒就是1秒
			System.out.println(i);
			stulist.clear();
			admlist.clear();
		}
		gameClearance();
	}

	public static void gameClearance() {
		String s = "\r      ┏┛ ┻━━━━━┛ ┻┓\r      ┃　　　　　　 ┃\r      ┃　　　━　　　┃\r      ┃　┳┛　  ┗┳　┃\r"
				+ "      ┃　　　　　　 ┃\r      ┃　　　┻　　　┃\r      ┃　　　　　　 ┃\r      ┗━┓　　　┏━━━┛\r        ┃　　　┃   系统自毁\r"
				+ "        ┃　　　┃   所有文件已经清除！\r        ┃　　　┗━━━━━━━━━┓\r        ┃　　　　　　　    ┣┓\r"
				+ "        ┃　　　　         ┏┛\r        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛\r          ┃ ┫ ┫   ┃ ┫ ┫\r          ┗━┻━┛   ┗━┻━┛";

		for (int i = 0; i < s.length(); i++) {
			System.out.print(s.charAt(i));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 输一个停一秒
		}
	}
}
