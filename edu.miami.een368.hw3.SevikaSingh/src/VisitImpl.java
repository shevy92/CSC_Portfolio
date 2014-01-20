

import java.text.SimpleDateFormat;
import java.util.Date;

public class VisitImpl<V, T> implements Visit<V, T> {

	V Visitor;
	T Host;
	Date visitDate;
	int patientRR;
	int doctorRR;
	
	public VisitImpl(V patientR, T doctorR) {
		Visitor = patientR;
		Host = doctorR;
		visitDate = null;
	}
	
	public VisitImpl(V patientR, T doctorR, Date visitR) {
		Visitor = patientR;
		Host = doctorR;
		visitDate = visitR;
	}
	
	public VisitImpl(int patientR, int doctorR, Date visitR) {
		patientRR = patientR;
		doctorRR = doctorR;
		visitDate = visitR;
	}

	SimpleDateFormat DOBformat = new SimpleDateFormat("MM/dd/yyyy");
	
	@Override
	public String getDate() {
		String visit;
	    visit = DOBformat.format(visitDate);	
		return visit;
	}

	@Override
	public T getHost() {
		return Host;
	}

	@Override
	public V getVisitor()  {		
		return Visitor;
	}
	
	public int getPID() {
		return patientRR;
	}
	
	public int getDID() {
		return doctorRR;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Host == null) ? 0 : Host.hashCode());
		result = prime * result + ((Visitor == null) ? 0 : Visitor.hashCode());
		result = prime * result
				+ ((visitDate == null) ? 0 : visitDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final VisitImpl<V,T> other = (VisitImpl<V,T>) obj;
		if (Host == null) {
			if (other.Host != null)
				return false;
		} else if (!Host.equals(other.Host))
			return false;
		if (Visitor == null) {
			if (other.Visitor != null)
				return false;
		} else if (!Visitor.equals(other.Visitor))
			return false;
		if (visitDate == null) {
			if (other.visitDate != null)
				return false;
		} else if (!visitDate.equals(other.visitDate))
			return false;
		return true;
	}
	

}
