package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class practice1 {
   public static void main(String[] args) {
      makeFrame mf = new makeFrame();
   }
}

class makeFrame extends JFrame implements KeyListener, Runnable {

   static int x, y;
   int f_width = 800;
   int f_height = 600;
   int bx = 0;

   boolean KeyUp = false;
   boolean KeyDown = false;
   boolean KeyLeft = false;
   boolean KeyRight = false;
   boolean KeySpace = false;
   boolean Key1 = false;
   
   Thread th;

   Toolkit tk = Toolkit.getDefaultToolkit();
   Image player = tk.getImage("ChocoboA.png");
   Image playerMissile = tk.getImage("playerMissile.png");
   Image enemyMissile1 = tk.getImage("enemyMissile1.png");
   Image enemyMissile2 = tk.getImage("enemyMissile2.png");
   Image enemyMissile3 = tk.getImage("enemyMissile3.png");
   Image enemy1 = tk.getImage("moogle.png");
   Image enemy2 = tk.getImage("sabotender.png");
   Image boss = tk.getImage("tonberi.png");
   Image background = new ImageIcon("back2.jpg").getImage();
   Image background1 = new ImageIcon("back2.jpg").getImage();
   Image[] Explo_img;

   int enemy1_w, enemy1_h, enemy2_w, enemy2_h, playerMissile_w, playerMissile_h, enemyMissile1_w, enemyMissile1_h,
         enemyMissile2_w, enemyMissile2_h, enemyMissile3_w, enemyMissile3_h, player_w, player_h, boss_w, boss_h;
   int playerStatus = 0;
   int cnt = 0;
   int game_Score;
   
   ArrayList<PlayerMissile> playerMissile_list = new ArrayList<PlayerMissile>();
   ArrayList<EnemyMissile> enemyMissile_list = new ArrayList<EnemyMissile>();
   ArrayList<Enemy1> enemy1_list = new ArrayList<Enemy1>();
   ArrayList<Enemy2> enemy2_list = new ArrayList<Enemy2>();
   ArrayList<Boss> boss_list = new ArrayList<Boss>();
   ArrayList<Explosion> explosion_list = new ArrayList<Explosion>();

   EnemyMissile em;
   PlayerMissile pms;
   Enemy1 en1;
   Enemy2 en2;
   Boss bo;
   Explosion ex;

   Image buffImage;
   Graphics buffg;
   
   makeFrame() {
      init();

      setTitle("chocobo 1945");
      setSize(f_width, f_height);

      Dimension screen = tk.getScreenSize();
      int f_x = (int) (screen.getWidth() / 2 - f_width / 2);
      int f_y = (int) (screen.getHeight() / 2 - f_height / 2);
      setLocation(f_x, f_y);
      setResizable(false);
      setVisible(true);
   }

   public void init() {
      x = 100;
      y = 100;

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      addKeyListener(this);
      Sound("chocobo.wav", true);
      th = new Thread(this);
      th.start();
      

      enemy1_w = ImageWidthValue("moogle2.png");
      enemy1_h = ImageHeightValue("moogle2.png");
      enemy2_w = ImageWidthValue("sabotender.png");
      enemy2_h = ImageHeightValue("sabotender.png");
      playerMissile_w = ImageWidthValue("playerMissile.png");
      playerMissile_h = ImageHeightValue("playerMissile.png");
      enemyMissile1_w = ImageWidthValue("enemyMissile1.png");
      enemyMissile1_h = ImageHeightValue("enemyMissile1.png");
      enemyMissile2_w = ImageWidthValue("enemyMissile2.png");
      enemyMissile2_h = ImageHeightValue("enemyMissile2.png");
      enemyMissile3_w = ImageWidthValue("enemyMissile3.png");
      enemyMissile3_h = ImageHeightValue("enemyMissile3.png");
      player_w = ImageWidthValue("chocobo.png");
      player_h = ImageHeightValue("chocobo.png");
      boss_w = ImageWidthValue("tonberi.png");
      boss_h = ImageHeightValue("tonberi.png");
      Explo_img = new Image[9];

      for (int i = 0; i < Explo_img.length; ++i) {

         Explo_img[i] =

               new ImageIcon("explosion" + i + ".png").getImage();

      }
   }

   @Override
   public void run() {
      try {
         while (true) {
            keyProcess();
         
            if (Key1==false){
            playerMissileProcess();
            enemyProcess();
            enemyMissileProcess();
            explosionProcess();
            repaint();
            Thread.sleep(20);
            cnt++;
         }
            
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void enemyProcess() {

//      enemyPosition(1, 10, 10, 150);
//      enemyPosition(2, 10, 10, 350);
//      enemyPosition(1, 200, 10, 150);
//      enemyPosition(1, 200, 10, 450);
//      enemyPosition(1, 300, 10, 200);
//      enemyPosition(1, 300, 10, 350);
//      enemyPosition(1, 380, 10, 150);
//      enemyPosition(1, 380, 10, 300);
//      enemyPosition(1, 380, 10, 450);
//      enemyPosition(2, 450, 10, 250);
//      enemyPosition(1, 650, 10, 150);
//      enemyPosition(1, 650, 10, 250);
//      enemyPosition(1, 650, 10, 350);
//      enemyPosition(1, 650, 10, 450);
//      enemyPosition(1, 720, 10, 200);
//      enemyPosition(1, 720, 10, 350);
//      enemyPosition(1, 720, 10, 500);
//      enemyPosition(1, 780, 10, 150);
//      enemyPosition(1, 780, 10, 250);
//      enemyPosition(2, 780, 10, 450);
//      enemyPosition(2, 820, 10, 150);
//      enemyPosition(1, 900, 10, 350);
//      enemyPosition(1, 900, 10, 450);
//      enemyPosition(2, 1050, 10, 150);
//      enemyPosition(2, 1050, 10, 250);
//      enemyPosition(2, 1050, 10, 450);
//      enemyPosition(1, 1250, 10, 150);
//      enemyPosition(1, 1250, 10, 250);
//      enemyPosition(1, 1250, 10, 350);
//      enemyPosition(1, 1250, 10, 450);
//      enemyPosition(1, 1320, 10, 200);
//      enemyPosition(1, 1320, 10, 350);
//      enemyPosition(1, 1320, 10, 500);
//      enemyPosition(1, 1450, 10, 150);
//      enemyPosition(1, 1500, 10, 250);
//      enemyPosition(2, 1550, 10, 250);
//      enemyPosition(1, 1550, 10, 350);
//      enemyPosition(1, 1600, 10, 450);
//      enemyPosition(1, 1650, 10, 350);
//      enemyPosition(1, 1700, 10, 250);
//      enemyPosition(1, 1750, 10, 150);
//      enemyPosition(2, 1900, 10, 150);
//      enemyPosition(2, 1900, 10, 400);
//      enemyPosition(2, 1900, 10, 450);
//      enemyPosition(2, 2200, 10, 150);
//      enemyPosition(2, 2200, 10, 200);
//      enemyPosition(2, 2200, 10, 400);
//      enemyPosition(2, 2200, 10, 450);

      if (cnt == 0) {
         bo = new Boss(f_width - 200, 150);
         boss_list.add(bo);
      }
   }

   public void enemyMissileProcess() {
      int n = 600;
      int m = 100;
      for (int i = 0; i < enemy1_list.size(); i++) {
         en1 = (Enemy1) enemy1_list.get(i);

         if (en1.x1 < -50) {
            enemy1_list.remove(i);
         }
         if (en1.enemyCount % 90 == 0 && en1.x1 > 300) {
            em = new EnemyMissile(en1.x1, en1.y1, 1, 0, 3, 0, 0, 0);
            enemyMissile_list.add(em);
         }
         en1.enemyMove();
      }
      for (int i = 0; i < enemy2_list.size(); i++) {
         en2 = (Enemy2) enemy2_list.get(i);

         if (en2.y1 < -20) {
            enemy2_list.remove(i);
         }
         if (en2.enemyCount % 500 == 10 || en2.enemyCount % 500 == 50 || en2.enemyCount % 500 == 70) {
            em = new EnemyMissile(en2.x1, en2.y1, 2, 0, 4, 0, 0, 0);
            enemyMissile_list.add(em);
            em = new EnemyMissile(en2.x1, en2.y1, 2, 15, 4, 0, 0, 0);
            enemyMissile_list.add(em);
            em = new EnemyMissile(en2.x1, en2.y1, 2, 30, 4, 0, 0, 0);
            enemyMissile_list.add(em);
            em = new EnemyMissile(en2.x1, en2.y1, 2, 350, 4, 0, 0, 0);
            enemyMissile_list.add(em);
            em = new EnemyMissile(en2.x1, en2.y1, 2, 330, 4, 0, 0, 0);
            enemyMissile_list.add(em);
         }
         en2.enemyMove1();
      }
      for (int i = 0; i < boss_list.size(); i++) {
         bo = (Boss) boss_list.get(i);
         bo.bossMove(cnt);
         if (bo.life == 0)
            boss_list.remove(i);
         if (bo.enemyCount % 200 == 50 || bo.enemyCount % 200 == 90 || bo.enemyCount % 200 == 130) {
            for (int j = 0; j < 8; j++) {
               em = new EnemyMissile(bo.x1 + boss_w / 2, bo.y1 + boss_h * 3 / 10, 1, 0, 5, 1, 0, 0);
               enemyMissile_list.add(em);
               em = new EnemyMissile(bo.x1 + boss_w / 2, bo.y1 + boss_h * 4 / 10, 1, 0, 5, 1, 0, 0);
               enemyMissile_list.add(em);
               em = new EnemyMissile(bo.x1 + boss_w / 2, bo.y1 + boss_h * 5 / 10, 1, 0, 5, 1, 0, 0);
               enemyMissile_list.add(em);
               em = new EnemyMissile(bo.x1 + boss_w / 2, bo.y1 + boss_h * 6 / 10, 1, 0, 5, 1, 0, 0);
               enemyMissile_list.add(em);
               em = new EnemyMissile(bo.x1 + boss_w / 2, bo.y1 + boss_h * 7 / 10, 1, 0, 5, 1, 0, 0);
               enemyMissile_list.add(em);
            }
         }

         if (bo.enemyCount % 1000 == 50 || bo.enemyCount % 1000 == 90 || bo.enemyCount % 1000 == 130) {
            for (int j = 0; j < 8; j++) {
               em = new EnemyMissile(bo.x1 + boss_w / 2, bo.y1 + boss_h / 2, 3, j * 15 - 60, 5, 0, 0, 0);
               enemyMissile_list.add(em);
            }
         }
         if (bo.enemyCount % 1000 == 200 || bo.enemyCount % 1000 == 240 || bo.enemyCount % 1000 == 280) {
            for (int j = 0; j < 24; j++) {
               em = new EnemyMissile(bo.x1 + boss_w / 2, bo.y1 + boss_h / 2, 3, j * 15, 5, 0, 0, 0);
               enemyMissile_list.add(em);
            }
         }
         if (bo.enemyCount % 1000 == 220 || bo.enemyCount % 1000 == 260 || bo.enemyCount % 1000 == 300) {
            for (int j = 0; j < 24; j++) {
               em = new EnemyMissile(bo.x1 + boss_w / 2, bo.y1 + boss_h / 2, 3, j * 15 - 7, 5, 0, 0, 0);
               enemyMissile_list.add(em);
            }
         }

         for (int k = 1; k <= 40; k++) {

            if (bo.enemyCount % 1000 == 350 + k * 10) {
               if (k <= 10) {
                  em = new EnemyMissile(n, m, 2, 0, 5, 1, 0, 40 - k);
                  enemyMissile_list.add(em);
                  n -= (k * 20);
                  m += (k * 20);

               } else if (k <= 20) {
                  em = new EnemyMissile(n, m, 2, 0, 5, 1, 0, 40 - k);
                  enemyMissile_list.add(em);
                  n += (k * 20);
                  m += (k * 20);

               } else if (k <= 30) {
                  em = new EnemyMissile(n, m, 2, 0, 5, 1, 0, 40 - k);
                  enemyMissile_list.add(em);
                  n += (k * 20);
                  m -= (k * 20);

               } else {
                  em = new EnemyMissile(n, m, 2, 0, 5, 1, 0, 40 - k);
                  enemyMissile_list.add(em);
                  n -= (k * 20);
                  m -= (k * 20);

               }
            }
         }
      }
   }

   public void enemyPosition(int type, int createTime, int x, int y) {
      switch (type) {
      case 1:
         if (cnt == createTime) {
            en1 = new Enemy1(f_width + x, y);
            enemy1_list.add(en1);
         }
         break;

      case 2:
         if (cnt == createTime) {
            en2 = new Enemy2(f_width + x, y);
            enemy2_list.add(en2);
         }
         break;
      }
   }

   public boolean Crash(int x1, int y1, int x2, int y2, int w1, int h1, int w2, int h2) {
      boolean check = false;
      if (x1 <= x2 + w2 && x2 < x1 + w1 && y1 + h1 > y2 && y1 < y2 + h2) {
         check = true;
      } else {
         check = false;
      }
      return check;
   }

   public boolean playerCrash(int x1, int y1, int x2, int y2, int w1, int h1, int w2, int h2) {
      boolean check = false;
      if (x1 + w1 >= x2 && x1 < x2 + w2 && y1 + h1 > y2 && y1 < y2 + h2) {
         check = true;
      } else {
         check = false;
      }
      return check;
   }

   public void playerMissileProcess() {
      if (KeySpace == true) {
         if (game_Score < 5000) {

            pms = new PlayerMissile(x, y, 0, 20, 0, 0);
            playerMissile_list.add(pms);
            Sound("missilefire.wav", false);
         } else if (game_Score < 15000) {
            pms = new PlayerMissile(x, y - 10, 0, 20, 0, 0);
            playerMissile_list.add(pms);
            Sound("missilefire.wav", false);
            pms = new PlayerMissile(x, y + 10, 0, 20, 0, 0);
            playerMissile_list.add(pms);
            Sound("missilefire.wav", false);
         } else if (game_Score < 30000) {
            pms = new PlayerMissile(x, y, 0, 20, 0, 0);
            playerMissile_list.add(pms);
            Sound("missilefire.wav", false);
            pms = new PlayerMissile(x, y, 30, 20, 0, 0);
            playerMissile_list.add(pms);
            Sound("missilefire.wav", false);
            pms = new PlayerMissile(x, y, 330, 20, 0, 0);
            playerMissile_list.add(pms);
            Sound("missilefire.wav", false);
         } else {
            pms = new PlayerMissile(x, y + 10, 10, 20, 0, 0);
            playerMissile_list.add(pms);
            Sound("missilefire.wav", false);
            pms = new PlayerMissile(x, y - 10, 350, 20, 0, 0);
            playerMissile_list.add(pms);
            Sound("missilefire.wav", false);
            pms = new PlayerMissile(x, y + 20, 20, 20, 0, 0);
            playerMissile_list.add(pms);
            Sound("missilefire.wav", false);
            pms = new PlayerMissile(x, y - 20, 340, 20, 0, 0);
            playerMissile_list.add(pms);
            Sound("missilefire.wav", false);
         }
      }
   }

   public void paint(Graphics g) {
      buffImage = createImage(f_width, f_height);
      buffg = buffImage.getGraphics();
      drawBackGround();
      drawPlayer();
      drawEnemy1();
      drawEnemy2();
      drawBoss();
      drawEnemyMissile();
      drawMissile();
      drawExplosion();
      Draw_StatusText();
      g.drawImage(buffImage, 0, 0, this);
   }

   public void drawExplosion() {

      for (int i = 0; i < explosion_list.size(); ++i) {

         ex = (Explosion) explosion_list.get(i);
         if (ex.type == 0) {
            if (ex.ex_cnt < 4) {
               buffg.drawImage(Explo_img[0], ex.x1 - Explo_img[0].getWidth(null) / 2, ex.y1, this);
            } else if (ex.ex_cnt < 8) {
               buffg.drawImage(Explo_img[1], ex.x1 - Explo_img[1].getWidth(null) / 2, ex.y1, this);
            } else if (ex.ex_cnt < 12) {
               buffg.drawImage(Explo_img[2], ex.x1 - Explo_img[2].getWidth(null) / 2, ex.y1, this);
            } else {
               explosion_list.remove(i);
               ex.ex_cnt = 0;
            }
         }

         else if (ex.type == 1) {
            if (ex.ex_cnt < 2) {
               buffg.drawImage(Explo_img[3], ex.x1 - Explo_img[3].getWidth(null) / 2, ex.y1, this);
            } else if (ex.ex_cnt < 4) {
               buffg.drawImage(Explo_img[4], ex.x1 - Explo_img[4].getWidth(null) / 2, ex.y1, this);
            } else if (ex.ex_cnt < 6) {
               buffg.drawImage(Explo_img[5], ex.x1 - Explo_img[5].getWidth(null) / 2, ex.y1, this);
            } else {
               explosion_list.remove(i);
               ex.ex_cnt = 0;
            }
         }

         else if (ex.type == 2) {
            if (ex.ex_cnt < 2) {
               buffg.drawImage(Explo_img[6], ex.x1 - Explo_img[6].getWidth(null) / 2, ex.y1, this);
            } else if (ex.ex_cnt < 4) {
               buffg.drawImage(Explo_img[7], ex.x1 - Explo_img[7].getWidth(null) / 2, ex.y1, this);
            } else if (ex.ex_cnt < 8) {
               buffg.drawImage(Explo_img[8], ex.x1 - Explo_img[8].getWidth(null) / 2, ex.y1, this);
            } else {
               explosion_list.remove(i);
               ex.ex_cnt = 0;
            }
         }
      }
   }

   public void drawBackGround() {
      int n = 0;
      buffg.clearRect(0, 0, f_width, f_height);
      if (bx > -4800) {
         for (int i = 0; i < 7; i++) {
            if (n < 4800) {
               buffg.drawImage(background, n + bx, 0, this);
            } else {
               buffg.drawImage(background1, n + bx, 0, this);
            }
            n += 800;

         }
         bx -= 2;
      } else
         buffg.drawImage(background1, 0, 0, this);
   }

   public void drawEnemy1() {
      for (int i = 0; i < enemy1_list.size(); i++) {
         en1 = (Enemy1) (enemy1_list.get(i));
         buffg.drawImage(enemy1, en1.x1, en1.y1, this);

      }
   }

   public void drawEnemy2() {
      for (int i = 0; i < enemy2_list.size(); i++) {
         en2 = (Enemy2) (enemy2_list.get(i));
         buffg.drawImage(enemy2, en2.x1, en2.y1, this);
      }
   }

   public void drawBoss() {
      for (int i = 0; i < boss_list.size(); i++) {
         bo = (boss_list.get(i));
         buffg.drawImage(boss, bo.x1, bo.y1, this);

      }
   }

   public void drawEnemyMissile() {
      for (int i = 0; i < enemyMissile_list.size(); i++) {
         em = (enemyMissile_list.get(i));

         if (em.missileX < -500 || em.missileX > 1100 || em.missileY < -200 || em.missileY > 800) {
            enemyMissile_list.remove(i);
         } else {
            switch (em.type) {
            case 1:

               if (playerCrash(x + 80, y + 30, em.missileX, em.missileY, player_w - 80, player_h - 55,
                     enemyMissile1_w, enemyMissile1_h)) {
                  ex = new Explosion(x + 30, y + 30, 1);
                  explosion_list.add(ex);
                  enemyMissile_list.remove(i);
                  Sound("explosion.wav", false);
//                  suspend();
               } else
                  buffg.drawImage(enemyMissile1, em.missileX - enemyMissile1_w, em.missileY, this);

               break;
            case 2:

               if (playerCrash(x + 80, y + 30, em.missileX, em.missileY, player_w - 80, player_h - 55,
                     enemyMissile1_w, enemyMissile1_h)) {
                  ex = new Explosion(x + 30, y + 30, 1);
                  explosion_list.add(ex);
                  enemyMissile_list.remove(i);
                  Sound("explosion.wav", false);
//                  suspend();
               } else
                  buffg.drawImage(enemyMissile2, em.missileX - enemyMissile1_w, em.missileY, this);

               break;
            case 3:

               if (playerCrash(x + 80, y + 30, em.missileX, em.missileY, player_w - 80, player_h - 55,
                     enemyMissile1_w, enemyMissile1_h)) {
                  ex = new Explosion(x + 30, y + 30, 1);
                  explosion_list.add(ex);
                  enemyMissile_list.remove(i);
                  Sound("explosion.wav", false);
//                  suspend();
               } else
                  buffg.drawImage(enemyMissile3, em.missileX - enemyMissile1_w, em.missileY, this);

               break;
            }
         }
         em.move();
      }
   }

   public void suspend() {
      Key1=true;
      buffg.setFont(new Font("Defualt", Font.BOLD, 80));
      buffg.setColor(Color.white);
      buffg.drawString("GAMA OVER", 100, 300);
      buffg.drawString(""+game_Score, 100, 400);
      
      
   }

   public void Draw_StatusText() { // 상태체크용텍스트를그립니다.

      buffg.setFont(new Font("Defualt", Font.BOLD, 20));
      buffg.setColor(Color.white);      
      buffg.drawString("SCORE : " + game_Score, 350, 70);

   }

   public void drawMissile() {
      for (int i = 0; i < playerMissile_list.size(); i++) {
         pms = (playerMissile_list.get(i));

         buffg.drawImage(playerMissile, pms.x1 + 50, pms.y1 + 30, this);
         pms.move();
         if (pms.x1 > f_width) {
            playerMissile_list.remove(i);
         }
         for (int k = 0; k < enemy1_list.size(); k++) {
            en1 = (Enemy1) enemy1_list.get(k);
            if (Crash(en1.x1, en1.y1, pms.x1, pms.y1, enemy1_w, enemy1_h, playerMissile_w, playerMissile_h)) {
               playerMissile_list.remove(i);
               enemy1_list.remove(k);
               game_Score += 500;
               ex = new Explosion(en1.x1 + enemy1_w / 2, en1.y1 + enemy1_h / 2, 1);
               explosion_list.add(ex);
               Sound("explosion.wav", false);

            }
         }
         for (int l = 0; l < enemy2_list.size(); l++) {
            en2 = (Enemy2) enemy2_list.get(l);
            if (Crash(en2.x1, en2.y1, pms.x1, pms.y1, enemy2_w, enemy2_h, playerMissile_w, playerMissile_h)) {
               playerMissile_list.remove(i);
               en2.life--;
               ex = new Explosion(en2.x1, en2.y1 + enemy2_h / 2, 0);
               explosion_list.add(ex);
               Sound("explosion.wav", false);
               if (en2.life == 0) {
                  enemy2_list.remove(l);
                  game_Score += 2000;
                  ex = new Explosion(en2.x1 + enemy2_w / 2, en2.y1 + enemy2_h / 2, 1);
                  explosion_list.add(ex);
                  Sound("explosion.wav", false);
               }
            }
         }

         for (int j = 0; j < boss_list.size(); j++) {
            bo = (Boss) boss_list.get(j);
            if (Crash(bo.x1 + 130, bo.y1, pms.x1, pms.y1, boss_w, boss_h, playerMissile_w, playerMissile_h)) {
               playerMissile_list.remove(i);
               bo.life--;
               ex = new Explosion(pms.x1 + 30, pms.y1, 0);
               explosion_list.add(ex);
               Sound("explosion.wav", false);
               if (bo.life == 0) {
                  boss_list.remove(j);
                  game_Score += 50000;
                  ex = new Explosion(bo.x1 + boss_w / 2, bo.y1 + boss_h / 2, 2);
                  explosion_list.add(ex);
                  Sound("explosion.wav", false);
               }
            }
         }

      }
   }

   public void explosionProcess() {
      for (int i = 0; i < explosion_list.size(); ++i) {

         ex = (Explosion) explosion_list.get(i);

         ex.effect();
      }
   }

   public void drawPlayer() {

      buffg.drawImage(player, x, y, this);
   }

   @Override
   public void keyTyped(KeyEvent e) {
   }

   @Override
   public void keyPressed(KeyEvent e) {

      switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
         KeyUp = true;
         break;
      case KeyEvent.VK_DOWN:
         KeyDown = true;
         break;
      case KeyEvent.VK_LEFT:
         KeyLeft = true;
         break;
      case KeyEvent.VK_RIGHT:
         KeyRight = true;
         break;      
      case KeyEvent.VK_SPACE:
         KeySpace = true;
         break;
      case KeyEvent.VK_1:
         Key1 = false;      
         break;

      }
   }

   @Override
   public void keyReleased(KeyEvent e) {
      switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
         KeyUp = false;
         break;
      case KeyEvent.VK_DOWN:
         KeyDown = false;
         break;
      case KeyEvent.VK_LEFT:
         KeyLeft = false;
         break;
      case KeyEvent.VK_RIGHT:
         KeyRight = false;
         break;
      case KeyEvent.VK_SPACE:
         KeySpace = false;
         break;
      }
   }

   public void keyProcess() {
      if (KeyUp == true)
         if (y > 0)
            y -= 5;
      if (KeyDown == true)
         if (y < 600 - player_h)
            y += 5;
      if (KeyLeft == true)
         if (x > 0)
            x -= 5;
      if (KeyRight == true)
         if (x < 800 - player_w)
            x += 5;
   }

   public int ImageWidthValue(String file) {
      int x1 = 0;
      try {
         File f = new File(file);
         BufferedImage bi = ImageIO.read(f);
         x1 = bi.getWidth();
      } catch (Exception e) {

      }
      return x1;
   }

   public int ImageHeightValue(String file) {
      int y1 = 0;
      try {
         File f = new File(file);
         BufferedImage bi = ImageIO.read(f);
         y1 = bi.getHeight();
      } catch (Exception e) {

      }
      return y1;
   }

   public void Sound(String file, boolean loop) {
      Clip clip;
      try {
         AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
         clip = AudioSystem.getClip();
         clip.open(ais);
         clip.start();
         if (loop) {
            clip.loop(-1);

         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}

class PlayerMissile {
   int x1, y1, type, extraY;
   int speed;
   float angle;

   PlayerMissile(int x1, int y1, float angle, int speed, int type, int extraY) {
      this.x1 = x1;
      this.y1 = y1;
      this.angle = angle;
      this.speed = speed;
      this.type = type;
      this.extraY = extraY;

   }

   public void move() {
      x1 += Math.cos(Math.toRadians(angle)) * speed;
      y1 += Math.sin(Math.toRadians(angle)) * speed;

   }
}

class EnemyMissile {
   int missileX, missileY, X, Y, guided;
   int enemyX, enemyY;
   int speed, type;
   float angle;
   int moveX, moveY;

   EnemyMissile(int enemyX, int enemyY, int type, float angle, int speed, int guided, int X, int Y) {
      this.enemyX = enemyX;
      this.enemyY = enemyY;
      missileX = enemyX;
      missileY = enemyY;
      this.X = X;
      this.Y = Y;
      this.type = type;
      this.angle = angle;
      this.speed = speed;
      this.guided = guided;
      this.moveX = (int) ((enemyX - makeFrame.x)
            / (Math.sqrt(Math.pow(enemyX - makeFrame.x, 2) + Math.pow(enemyY - makeFrame.y, 2)) / speed));
      this.moveY = (int) ((enemyY - makeFrame.y)
            / (Math.sqrt(Math.pow(enemyX - makeFrame.x, 2) + Math.pow(enemyY - makeFrame.y, 2)) / speed));
   }

   public void move() {
      if (guided == 0) {
         missileX -= Math.cos(Math.toRadians(angle)) * speed;
         missileY += Math.sin(Math.toRadians(angle)) * speed;
      } else if (guided == 1) {
         if (Y > 0) {
            missileX -= 0;
            missileY -= 0;
            Y--;
         } else {
            missileX -= moveX;
            missileY -= moveY;
         }
      }
   }
}

class Enemy1 {
   int x1, y1;
   int enemyCount = 0;

   Enemy1(int x1, int y1) {
      this.x1 = x1;
      this.y1 = y1;
   }

   public void enemyMove() {
      if (x1 > -110)
         x1 -= 1;
      if ((enemyCount) % 60 <= 30) {
         y1 -= 3;
      } else
         y1 += 3;

      enemyCount++;
   }
}

class Enemy2 {
   int x1, y1;
   int life = 25;
   int enemyCount = 0;

   Enemy2(int x1, int y1) {
      this.x1 = x1;
      this.y1 = y1;
   }

   public void enemyMove1() {

      if (x1 > 700)
         x1 -= 3;
      else if (enemyCount > 200)
         y1 -= 3;
      enemyCount++;
   }
}

class Boss {
   int x1, y1;
   int life = 2000;
   int enemyCount;

   Boss(int x1, int y1) {
      this.x1 = x1;
      this.y1 = y1;
   }

   public void bossMove(int cnt) {

      if (enemyCount % 500 <= 25) {
         if (x1 > 300)
            x1 -= 2;
      } else {

      }
      enemyCount++;
   }
}

class Explosion {
   int x1, y1, ex_cnt, type;

   Explosion(int x1, int y1, int type) {
      this.x1 = x1;
      this.y1 = y1;
      this.type = type;
      ex_cnt = 0;
   }

   public void effect() {
      ex_cnt++;
   }
}