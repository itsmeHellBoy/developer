package maven1.general.logic.impl;

import java.sql.Blob;

import javax.inject.Inject;
import javax.inject.Named;

import maven1.general.dataaccess.api.BinaryObjectEntity;
import maven1.general.dataaccess.api.dao.BinaryObjectRepository;
import maven1.general.logic.api.UcManageBinaryObject;
import maven1.general.logic.api.to.BinaryObjectEto;
import maven1.general.logic.base.AbstractUc;

/**
 * Implementation of {@link UcManageBinaryObject}.
 */
@Named
public class UcManageBinaryObjectImpl extends AbstractUc implements UcManageBinaryObject {

  private BinaryObjectRepository binaryObjectRepository;

  /**
   * @return {@link BinaryObjectRepository} instance.
   */
  public BinaryObjectRepository getBinaryObjectRepository() {

    return this.binaryObjectRepository;
  }

  /**
   * @param binaryObjectRepository the {@link BinaryObjectRepository} to set
   */
  @Inject
  public void setBinaryObjectRepository(BinaryObjectRepository binaryObjectRepository) {

    this.binaryObjectRepository = binaryObjectRepository;
  }

  @Override
  public BinaryObjectEto saveBinaryObject(Blob data, BinaryObjectEto binaryObjectEto) {

    BinaryObjectEntity binaryObjectEntity = getBeanMapper().map(binaryObjectEto, BinaryObjectEntity.class);
    binaryObjectEntity.setData(data);
    this.binaryObjectRepository.save(binaryObjectEntity);
    return getBeanMapper().map(binaryObjectEntity, BinaryObjectEto.class);
  }

  @Override
  public void deleteBinaryObject(Long binaryObjectId) {

    this.binaryObjectRepository.deleteById(binaryObjectId);

  }

  @Override
  public BinaryObjectEto findBinaryObject(Long binaryObjectId) {

    return getBeanMapper().map(this.binaryObjectRepository.find(binaryObjectId), BinaryObjectEto.class);
  }

  @Override
  public Blob getBinaryObjectBlob(Long binaryObjectId) {

    return this.binaryObjectRepository.find(binaryObjectId).getData();
  }

}
