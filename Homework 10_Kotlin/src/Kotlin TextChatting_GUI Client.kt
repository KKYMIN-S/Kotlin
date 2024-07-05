import java.awt.Color
import java.awt.Container
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.InetAddress
import java.net.Socket
import javax.swing.*
import javax.swing.border.Border

class TextChatting_Socket_Client_GUI(var port_no : Int) : JFrame() {
    var jtxt_display_area: JTextArea? = null
    val jtxt_serv_addr: JTextField? = null
    val jtxt_cli_addr: JTextField? = null
    var jtxt_msg_input_area: JTextArea? = null
    val input_msg: String? = null
    var sockDataInputstream: DataInputStream? = null
    var sockDataOutputstream: DataOutputStream? = null

    init { // constructor
        setTitle("Kotlin Swing-based TextChatting_Client")
        setDefaultCloseOperation(EXIT_ON_CLOSE)
        val gridLayout = GridLayout(7, 2, 5, 5)
        //gridLayout.setVgap(5); // vertical gap
        val c: Container = contentPane
        c.setBackground(Color.LIGHT_GRAY)
        c.setLayout(FlowLayout())
        val pnl_addr = JPanel()
        val border_addr: Border = BorderFactory.createTitledBorder("Server/Client Address")
        pnl_addr.setBorder(border_addr)
        c.add(pnl_addr)
        val l_serv_addr = JLabel("Server Addr")
        pnl_addr.add(l_serv_addr)
        val tf_serv_addr = JTextField("127.0.0.1")
        tf_serv_addr.setBackground(Color.YELLOW)
        pnl_addr.add(tf_serv_addr)
        val l_cli_addr = JLabel("Client Addr")
        pnl_addr.add(l_cli_addr)
        val tf_cli_addr = JTextField("127.0.0.1")
        tf_cli_addr.setBackground(Color.YELLOW)
        pnl_addr.add(tf_cli_addr)
        val pnl_display_area = JPanel()
        val border_text_area: Border = BorderFactory.createTitledBorder("Program Progress / Received Message")
        pnl_display_area.setBorder(border_text_area)
        c.add(pnl_display_area)
        val display_area = JTextArea("Constructor executed...\n", 15, 30)
        pnl_display_area.add(JScrollPane(display_area))

        jtxt_display_area = display_area
        val pnl_message_input_area = JPanel()
        val border_message_input_area: Border = BorderFactory.createTitledBorder("Input message to be sent")
        pnl_message_input_area.setBorder(border_message_input_area)
        c.add(pnl_message_input_area)
        val msg_input_area = JTextArea("Sample mesage to be sent to server", 3, 30)
        pnl_message_input_area.add(JScrollPane(msg_input_area))
        jtxt_msg_input_area = msg_input_area
        val send_button = JButton("Send Text Message to Server")
        //send_button.setLocation(100, 100);
        send_button.setSize(150, 50)
        send_button.setBackground(Color.GREEN)
        send_button.addActionListener(ActionHandler())
        c.add(send_button)
        setSize(400, 600)
        isVisible = true
    }

    fun setDataInputStream(sockDinStr: DataInputStream) {
        sockDataInputstream = sockDinStr
    }

    fun setDataOutputStream(sockDoutStr: DataOutputStream) {
        sockDataOutputstream = sockDoutStr
    }
    private  inner class ActionHandler: ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            val b = e.source as JButton
            if (b.text == "Send Text Message to Server") {
                val input_msg = jtxt_msg_input_area!!.getText()
                jtxt_display_area!!.append("<< $input_msg\n")
                jtxt_msg_input_area!!.text = ""
                try {
                    sockDataOutputstream!!.writeUTF(input_msg)
                }
                catch (e1: IOException) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace()
                }
            }
        }
    }
}

@Throws(IOException::class)
fun main(agrs: Array<String>) {
    var inetAddr: InetAddress? = null
    var myAddr: InetAddress? = null
    // establishing the connection
    val serv_port_no = 5056
    var servSocket: Socket? = null
    var sockDataInputstream: DataInputStream? = null
    var sockDataOutputstream: DataOutputStream? = null
    val gui_TxtChat_Server = TextChatting_Socket_Client_GUI(serv_port_no)
    //gui_TxtChatting.
    myAddr = InetAddress.getByName("localhost")
    System.out.printf("Socket Client:: My inetaddress = %s\n", myAddr)
    gui_TxtChat_Server.jtxt_display_area!!.append("Socket Client:: client_inetaddr = $myAddr\n")
    gui_TxtChat_Server.jtxt_display_area!!.append("Socket Client:: Trying to connect server ...\n")
    servSocket = Socket("127.0.0.1", serv_port_no)
    val serv_addr = servSocket.getLocalSocketAddress()
    System.out.printf("Socket Client:: Socket is opened ....\n")
    gui_TxtChat_Server.jtxt_display_area!!.append("Socket Client:: Socket is opened ....\n")
    // getting client request
    if (servSocket == null) {
        println("Error in socket creation/opening, cliSocket is null !!")
        return
    } else {
        inetAddr = servSocket.getInetAddress()
        System.out.printf("Socket client :: connected to server ($servSocket) ....\n")
    }
    gui_TxtChat_Server.jtxt_display_area!!.append("Socket client :: connected to server ($servSocket) ....\n")

    try {
        // mynewSocket object to receive incoming client requests
        sockDataInputstream = DataInputStream(servSocket.getInputStream())
        gui_TxtChat_Server.setDataInputStream(sockDataInputstream)
        sockDataOutputstream = DataOutputStream(servSocket.getOutputStream())
        gui_TxtChat_Server.setDataOutputStream(sockDataOutputstream)
        System.out.printf("Socket_client is ready now ....\n")
        gui_TxtChat_Server.jtxt_display_area!!.append("Socket_client is ready now ....\n")
        var recvMsgStr: String
        var msgStrToSent: String
        while (true) {
            try {
                // getting answers from client
                recvMsgStr = sockDataInputstream.readUTF()
                if (recvMsgStr == "Exit") {
                    println("Server $servSocket sent exit...")
                    println("Connection closing...")
                    gui_TxtChat_Server.jtxt_display_area!!.append("Server $servSocket sent exit...")
                    gui_TxtChat_Server.jtxt_display_area!!.append("Connection closing...")
                    if (servSocket != null) servSocket.close()
                    println("Closed")
                    gui_TxtChat_Server.jtxt_display_area!!.append("Closed")
                    break }
                gui_TxtChat_Server.jtxt_display_area!!.append(">> $recvMsgStr\n")
            }
            catch (e: IOException) {
                e.printStackTrace()
                break
            }
        }
        try {
            // closing resources
            sockDataInputstream.close()
            sockDataOutputstream.close()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
    }
    catch (e: Exception) {
        if (servSocket != null) servSocket.close()
        servSocket.close()
        e.printStackTrace()
    }
}


