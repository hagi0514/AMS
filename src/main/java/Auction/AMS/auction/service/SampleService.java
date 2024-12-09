package Auction.AMS.auction.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import Auction.AMS.auction.entity.Sample;
import Auction.AMS.auction.mapper.SampleMapper;


@Service
public class SampleService {
    @Autowired
    private SampleMapper SampleMapper;

    public void addSample(Sample sample){
        SampleMapper.addSample(sample);
    }

    public List<Sample> sampleList(){
        return SampleMapper.sampleList();
    }

    public Sample getSampleById(Long id){
        return SampleMapper.getSampleById(id);
    }

    public void updateSample(Sample sample){
        SampleMapper.updateSample(sample);
    }
}
