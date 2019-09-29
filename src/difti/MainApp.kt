package difti

import difti.display.Display
import difti.networkinterface.SocketServer
import processing.core.PApplet
import processing.core.PImage

class MainApp : PApplet() {
    lateinit var display: Display
    lateinit var server: SocketServer
    lateinit var backgroundImage: PImage

    override fun settings() {
        fullScreen()
    }

    override fun setup() {
        backgroundImage = loadImage("digitalgraffiti.png")
        display = Display(this, width, height)
        server = SocketServer(display, this)
        val tempPixelArray = ArrayList<ArrayList<Pixel>>()
        for (y in 0 until 480) {
            tempPixelArray.add(ArrayList())
            for (x in 0 until 640) {
                tempPixelArray[y].add(Pixel((Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt()))
            }
        }
        val tempImage = GImage(this, PixelArrayImage(tempPixelArray), width/2f, height/2f, 1f)
        val tempImage2 = GImage(this, PixelArrayImage(tempPixelArray), width/2f, height/2f, 0f)
        //display.addImage(tempImage)
        //display.addImage(tempImage2)
    }

    override fun draw() {
        background(backgroundImage)
        display.drawToScreen(this)
    }
}

fun main() {
    PApplet.main(MainApp::class.java)
}