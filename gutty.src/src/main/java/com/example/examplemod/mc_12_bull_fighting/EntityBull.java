package com.example.examplemod.mc_12_bull_fighting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by masatoshi on 2017/04/25.
 */
public class EntityBull extends EntityMob {
	public static final int ENTITY_ID = 1;

	public EntityBull(World worldIn){
		super(worldIn);

		tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackMelee(this, 1, false));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0));
		tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0, false));
		tasks.addTask(7, new EntityAIWander(this, 1.0));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(8, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityPlayer.class}));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0);
	}

	@Override
	public void onEntityUpdate(){
		if(!worldObj.isRemote){
			Entity target = getAttackTarget();
			if(target == null){
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
			}else{
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
			}
		}
		super.onEntityUpdate();
	}
}
