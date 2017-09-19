package net.endpoint.institute.model.anatomy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.endpoint.institute.model.InsDocument;

@Entity
@DiscriminatorValue("A")
public class InsAnatomyDocument extends InsDocument{

	private static final long serialVersionUID = -2383500042733790107L;
	@ManyToOne
	@JoinColumn(name="ins_body_part_id")
	private InsBodyPart part;
	
}
