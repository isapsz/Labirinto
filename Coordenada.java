public class Coordenada implements Cloneable
{
    private int x;
    private int y;

    public Coordenada(int x, int y) throws Exception
    {
		if(x < 0 || y < 0)
			throw new Exception("Coordenadas inválidas!");

		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public void setX(int x)	throws Exception
	{
		if(x < 0)
			throw new Exception("Coordenada x inválida!");
		this.x = x;
	}

	public void setY(int y)	throws Exception
	{
		if(y < 0)
			throw new Exception("Coordenada y inválida!");
		this.y = y;
	}

	public String toString()
	{
		return "A coordenada é (" + this.x + "," + this.y + ")";
	}

	public boolean equals(Object obj)
	{
		if(obj == null)
		return false;
		if(obj == this)
		return true;

		if(obj.getClass() != this.getClass())
		return false;

		Coordenada cd = (Coordenada)obj;
		if(this.x != cd.x)
		return false;
		if(this.y != cd.y)
		return false;

		return true;
	}

	public int hashCode()
	{
		int ret = 2;

		ret = ret * 3 + ((Integer)this.x).hashCode();
		ret = ret * 3 + ((Integer)this.y).hashCode();

		return ret;
	}

	public Coordenada(Coordenada model) throws Exception
	{
		if(model == null)
		 throw new Exception("Modelo ausente!");
	   this.x = model.x;
	   this.y = model.y;
    }

    public Object clone()
    {
		Coordenada ret= null;
		try
		{
			ret = new Coordenada(this);
		}
		catch(Exception erro)
		{}
		return ret;
	}
}