/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jnativehook.GlobalScreen
 *  org.jnativehook.NativeHookException
 *  org.jnativehook.keyboard.NativeKeyEvent
 *  org.jnativehook.keyboard.NativeKeyListener
 *  org.jnativehook.mouse.NativeMouseEvent
 *  org.jnativehook.mouse.NativeMouseListener
 *  org.jnativehook.mouse.NativeMouseMotionListener
 *  org.jnativehook.mouse.NativeMouseWheelEvent
 *  org.jnativehook.mouse.NativeMouseWheelListener
 */
import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;
import org.jnativehook.mouse.NativeMouseMotionListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

public class App {

    private Result result;
    private boolean suspended;
    private boolean unlocking;
    private boolean peeking;
    private boolean fuu = false;
    private List<String> searchResults;
    static private boolean fick = false;
    private int resultIdx;
    static Point p1, p2;
    static int count = 0;

    public App() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                App.this.result = new Result();
            }
        });
        this.suspended = true;
        this.unlocking = false;
        this.peeking = false;
    }

    public static void openApp(final App app) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (count >= 4 && fick) {
//                        System.out.println("open sesame");
                        app.suspended = false;
                        app.result.setVisible(true);
                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                                while (!app.suspended && !app.peeking) {
                                    try {
                                        app.result.toFront();
                                        Thread.sleep(50);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Result.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }).start();
                        count = 0;
                        fick = false;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread.start();
    }

    public static void main(String[] args) throws IOException {
        final App app = new App();
//        if (true) {
        openApp(app);
        final FileProcess fileProc = new FileProcess();
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);
        try {
            GlobalScreen.registerNativeHook();
            System.out.println("Dang ki thanh cong");
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeMouseListener((NativeMouseListener) new NativeMouseListener() {

            public void nativeMouseClicked(NativeMouseEvent nme) {
            }

            public void nativeMousePressed(NativeMouseEvent nme) {
                if (app.suspended && nme.getButton() == 1 && MouseInfo.getPointerInfo().getLocation().getX() <= 0.0 && MouseInfo.getPointerInfo().getLocation().getY() >= Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 1.0) {
                    fick = true;
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            app.unlocking = true;
                            if (count <= 4) {
                                return;
                            }
                            count = 0;
//                            for (int i = 0; i < 40; ++i) {
//                                if (app.unlocking && MouseInfo.getPointerInfo().getLocation().getX() <= 0.0 && MouseInfo.getPointerInfo().getLocation().getY() >= Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 1.0) {
//                                    try {
//                                        System.out.println(i);
//                                        Thread.sleep(100);
//                                    } catch (InterruptedException ex) {
//                                        Logger.getLogger(Result.class.getName()).log(Level.SEVERE, null, ex);
//                                    }
//                                    continue;
//                                }
//                                return;
//                            }
                            System.out.println("open sesame");
                            app.suspended = false;
                            app.result.setVisible(true);
                            new Thread(new Runnable() {

                                @Override
                                public void run() {
                                    while (!app.suspended && !app.peeking) {
                                        try {
                                            app.result.toFront();
                                            Thread.sleep(50);
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(Result.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            }).start();
                        }

                    }).start();
                }
                if (nme.getButton() == 1) {
                    p1 = MouseInfo.getPointerInfo().getLocation();
                } else if (nme.getButton() == 2) {
                    if (!app.suspended) {
                        app.result.setVisible(true);
                    }
                    p2 = MouseInfo.getPointerInfo().getLocation();
//                    System.out.println("-"+p2.x);
                    try {
                        String text = "";
//                            String text = new Tessst().getText(p1, p2);Vietnamese
                        String source = "Driver";
                        String position = "" + p1.x + " " + p1.y + " " + p2.x + " " + p2.y;
                        Process process = Runtime.getRuntime()
                                .exec(source + "\\" + "Platform_Flash.exe" + " -l English --screen-rect \"" + position + "\"");
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(process.getInputStream(), "UTF-8"));
                        String line = null;
                        if ((line = in.readLine()) != null) {
                            System.out.println(line);
                            text = line;
                        }
                        String temp = Normalizer.normalize(text, Normalizer.Form.NFD);
                        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
//                            JOptionPane.showMessageDialog(null, pattern.matcher(temp).replaceAll(""));
                        app.result.getLblQuery().setText(pattern.matcher(temp).replaceAll(""));
                        app.searchResults = fileProc.search(pattern.matcher(temp).replaceAll(""));
                        app.resultIdx = 0;
                        if (!app.searchResults.isEmpty()) {
                            String searchResult = (String) app.searchResults.get(app.resultIdx);
                            String[] substring = searchResult.split("\\|", 2);
                            app.result.getLblQ().setText(substring[0]);
                            app.result.getLblA().setText(substring[1]);
                        } else {
                            app.result.getLblQ().setText("N/A");
                            app.result.getLblA().setText("N/A");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            public void nativeMouseReleased(NativeMouseEvent nme) {
                if (nme.getButton() == 1) {
                    app.unlocking = false;
                }
            }

        });
        GlobalScreen.addNativeMouseMotionListener((NativeMouseMotionListener) new NativeMouseMotionListener() {

            public void nativeMouseMoved(NativeMouseEvent nme) {
                if (nme.getX() >= Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 20) {
                    app.result.setVisible(false);
                    app.suspended = true;
                    count = 0;
                }
                if (nme.getY() >= Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 20) {
                    app.result.setVisible(false);
                }
            }

            public void nativeMouseDragged(NativeMouseEvent nme) {
            }
        });
        GlobalScreen.addNativeMouseWheelListener((NativeMouseWheelListener) new NativeMouseWheelListener() {

            public void nativeMouseWheelMoved(NativeMouseWheelEvent nmwe) {
                if (app.searchResults == null || app.searchResults.isEmpty()) {
                    return;
                }
                String searchResult = "";
                if (nmwe.getWheelRotation() > 0) {
                    if (app.resultIdx < app.searchResults.size() - 1) {
                        searchResult = (String) app.searchResults.get(++app.resultIdx);
                        String[] substring = searchResult.split("\\|", 2);
                        app.result.getLblQ().setText(substring[0]);
                        app.result.getLblA().setText(substring[1]);
                    }
                } else if (nmwe.getWheelRotation() < 0 && app.resultIdx > 0) {
                    searchResult = (String) app.searchResults.get(--app.resultIdx);
                    String[] substring = searchResult.split("\\|", 2);
                    app.result.getLblQ().setText(substring[0]);
                    app.result.getLblA().setText(substring[1]);
                }
            }
        });
        GlobalScreen.addNativeKeyListener((NativeKeyListener) new NativeKeyListener() {

            public void nativeKeyPressed(NativeKeyEvent nke) {
                if (!app.suspended && nke.getKeyCode() == 29) {
                    app.peeking = true;
                    app.result.setVisible(true);
                    app.result.toFront();
                }
            }

            public void nativeKeyReleased(NativeKeyEvent nke) {
                if (!app.suspended && nke.getKeyCode() == 29) {
                    app.peeking = false;
                    app.result.setVisible(false);
                }
                if (nke.getKeyCode() == 88) {
                    System.exit(0);
                }
                if (nke.getKeyCode() == 42) {
                    app.result.getLblQuery().setText("");
                    app.result.getLblQ().setText("");
                    app.result.getLblA().setText("");
                }
                if (nke.getKeyCode() == 68) {
                    try {
                        BufferedImage screenFullImage = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                        new File("Screenshots").mkdirs();
                        ImageIO.write((RenderedImage) screenFullImage, "png", new File("Screenshots/" + System.currentTimeMillis() + ".png"));
                    } catch (AWTException | IOException ex) {
                        Logger.getLogger(Result.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (nke.getKeyCode() == 1) {
                    app.result.refresh();
                    app.suspended = true;
                    app.unlocking = false;
                    app.peeking = false;
                    app.searchResults = null;
                    app.resultIdx = 0;
                }
                if (nke.getKeyCode() == 2) {
                    p1 = MouseInfo.getPointerInfo().getLocation();
                } else if (nke.getKeyCode() == 3) {
//                    p2 = MouseInfo.getPointerInfo().getLocation();
//                    try {
//                        String text = new Tessst().getText(p1, p2);
//                        app.result.getLblQuery().setText(text);
//                        app.searchResults = fileProc.search(app.result.getLblQuery().getText());
//                        app.resultIdx = 0;
//                        if (!app.searchResults.isEmpty()) {
//                            String searchResult = (String) app.searchResults.get(app.resultIdx);
//                            String[] substring = searchResult.split("\\|", 2);
//                            app.result.getLblQ().setText(substring[0]);
//                            app.result.getLblA().setText(substring[1]);
//                        } else {
//                            app.result.getLblQ().setText("N/A");
//                            app.result.getLblA().setText("N/A");
//                        }
//                    } catch (AWTException ex) {
//                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (TesseractException ex) {
//                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                }
                if (nke.getKeyCode() == 62 && count <= 5) {
                    count++;
                    System.out.println(count);
                } else {
                    count = 0;
                }

            }

            public void nativeKeyTyped(NativeKeyEvent nke) {
//                String input = app.result.getLblQuery().getText();
////                if (nke.getRawCode() == 8) {
////                    if (!input.isEmpty()) {
////                        app.result.getLblQuery().setText(input.substring(0, input.length() - 1));
////                    }
////                } else {
////                    app.result.getLblQuery().setText(input + nke.getKeyChar());
////                }
//                app.searchResults = fileProc.search(app.result.getLblQuery().getText());
//                app.resultIdx = 0;
//                if (!app.searchResults.isEmpty()) {
//                    String searchResult = (String) app.searchResults.get(app.resultIdx);
//                    String[] substring = searchResult.split("\\|", 2);
//                    app.result.getLblQ().setText(substring[0]);
//                    app.result.getLblA().setText(substring[1]);
//                } else {
//                    app.result.getLblQ().setText("N/A");
//                    app.result.getLblA().setText("N/A");
//                }
            }
        });
    }
}
