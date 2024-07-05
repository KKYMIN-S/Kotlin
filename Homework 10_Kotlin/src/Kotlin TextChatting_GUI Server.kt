import java.awt.Color
import java.awt.Container
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket
import javax.swing.*
import javax.swing.border.Border

class TextChatting_Socket_Server_GUI(val port_no: Int) : JFrame() {
    var sockDataInputstream: DataInputStream? = null
    var sockDataOutputstream: DataOutputStream? = null
    val jtxt_display_area: JTextArea
    var jtxt_msg_input_area: JTextArea? = null
    val input_msg: String? = null
    init { // constructor
        setTitle("Kotlin Swing-based TextChatting_Server")
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
        jtxt_display_area = JTextArea("Constructor executed...\n", 15, 30)
        pnl_display_area.add(JScrollPane(jtxt_display_area))
        //display_area.append("\nadded line"); // for testing only
        val pnl_message_input_area = JPanel()
        val border_message_input_area: Border = BorderFactory.createTitledBorder("Input message to be sent")
        pnl_message_input_area.setBorder(border_message_input_area)
        c.add(pnl_message_input_area)
        val msg_input_area = JTextArea("Sample message to be sent to client", 3, 30)
        pnl_message_input_area.add(JScrollPane(msg_input_area))
        jtxt_msg_input_area = msg_input_area
        val send_button = JButton("Send Text Message to Client")
        //send_button.setLocation(100, 100);
        send_button.setSize(150, 50)
        send_button.setBackground(Color.GREEN)
        send_button.addActionListener(ActionHandler(jtxt_display_area))
        c.add(send_button)
        setSize(400, 600)
        isVisible = true }

    fun setDataInputStream(sockDinStr: DataInputStream) {
        sockDataInputstream = sockDinStr
    }
    fun setDataOutputStream(sockDoutStr: DataOutputStream) {
        sockDataOutputstream = sockDoutStr
    }
    private inner class ActionHandler(jtxt_display_area : JTextArea) : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            val b = e.source as JButton
            if (b.text == "Send Text Message to Client") {
                val input_msg = jtxt_msg_input_area!!.getText()
                jtxt_display_area.append("<< $input_msg\n")
                jtxt_msg_input_area!!.text = "" // clear input text message area
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
    var cliSocket: Socket? = null
    val port_no: Int = 5056
    val gui_TxtChat_Server = TextChatting_Socket_Server_GUI(port_no)
    //gui_TxtChatting.
    val servSocket = ServerSocket(port_no)
    val serv_addr = servSocket.getLocalSocketAddress()
    System.out.printf("Socket Server:: ServerSocket (serv_addr: %s) is opened.\n", serv_addr)
    gui_TxtChat_Server.jtxt_display_area!!.append("Socket Server ($serv_addr):: ServerSocket is opened.\n")
    // getting client request
    System.out.printf("Socket Server:: waiting for a client's connection request ....\n")
    gui_TxtChat_Server.jtxt_display_area!!.append("Socket Server:: waiting for a client's connection request ....\n")
    try {
        cliSocket = servSocket.accept()
        val sockDataInputstream = DataInputStream(cliSocket!!.getInputStream())
        val sockDataOutputstream = DataOutputStream(cliSocket!!.getOutputStream())
        System.out.printf("Socket Server:: Connected to a client: %s\n", cliSocket)
        gui_TxtChat_Server.jtxt_display_area!!.append("Socket Server:: Connected to a client$cliSocket\n")

        gui_TxtChat_Server.setDataInputStream(sockDataInputstream)
        gui_TxtChat_Server.setDataOutputStream(sockDataOutputstream)

        var recvMsgStr: String
        var msgStrToSent: String
        while (true) {
            try {
                recvMsgStr = sockDataInputstream.readUTF()
                if (recvMsgStr == "Exit") {
                    println("Client $cliSocket sends exit...")
                    println("Connection closing...")
                    gui_TxtChat_Server.jtxt_display_area!!.append("Client $cliSocket sends exit...")
                    gui_TxtChat_Server.jtxt_display_area!!.append("Connection closing...")
                    cliSocket.close()
                    println("Closed")
                    gui_TxtChat_Server.jtxt_display_area!!.append("Closed")
                    break
                }
                gui_TxtChat_Server.jtxt_display_area!!.append(">> $recvMsgStr\n")
            }
            catch (e: IOException) {
                e.printStackTrace()
                break
            }
        }
        try {
            sockDataInputstream.close()
            sockDataOutputstream.close()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
    }
    catch (e: Exception) {
        cliSocket?.close()
        servSocket.close()
        e.printStackTrace ()
    }
}



