import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;

public class SAMPLE5
{
	public static void main(String args[]) throws Exception
	{
		// Create I2C bus
		I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
		// Get I2C device, MCP23008 I2C address is 0x20(32)
		I2CDevice device = bus.getDevice(0x20);
		
		// Configure all pins as input
		device.write(0x00, (byte)0xFF);
		
		// Pull up enable on all pins of Port A and Port B
		device.write(0x06, (byte)0xFF);
		
		byte status = (byte)device.read(0x12);
		byte data = 0x01;
		
		for(int i = 0; i < 8; i++)
		{
			byte state = (byte)(status & data) ;
		if(state == data)
		{
			System.out.printf("GPIO pin %d of PORT A is HIGH %n", i);
		}
		else
		{
			System.out.printf("GPIO pin %d of PORT A is LOW %n", i);
		}
		data = (byte)(data << 1);
		}
	}
}
